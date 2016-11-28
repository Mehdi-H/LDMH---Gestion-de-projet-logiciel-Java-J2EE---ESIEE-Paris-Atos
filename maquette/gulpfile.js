var   gulp = require('gulp'),
        browserSync = require('browser-sync'),
        sass   = require('gulp-sass'),
        prefix = require('gulp-autoprefixer'),
        cp  = require('child_process'),
        pug = require('gulp-pug'),
        uglify = require('gulp-uglify'),
        concat = require('gulp-concat');

var jekyll   = process.platform === 'win32' ? 'jekyll.bat' : 'jekyll';
var messages = {
    jekyllBuild: '<span style="color: grey">Running:</span> $ jekyll build'
};

/**
 * Build the Jekyll Site
 */
gulp.task('jekyll-build', function (done) {
    browserSync.notify(messages.jekyllBuild);
    return cp.spawn( jekyll, ['build'], {stdio: 'inherit'})
        .on('close', done);
});

/**
 * Rebuild Jekyll & do page reload
 */
gulp.task('jekyll-rebuild', ['jekyll-build'], function () {
    browserSync.reload();
});

/**
 * Wait for jekyll-build, then launch the Server
 */
gulp.task('browser-sync', ['sass', 'jekyll-build'], function() {
    browserSync({
        server: {
            baseDir: '_site'
        },
        notify: false
    });
});

/**
 * Compile files from _scss into both _site/css (for live injecting) and site (for future jekyll builds)
 */
gulp.task('sass', function () {
    return gulp.src('assets/css/main.scss')
        .pipe(sass({
            includePaths: ['css'],
            errLogToConsole: true,
            onError: browserSync.notify
        }))
        .pipe(prefix(['last 15 versions', '> 1%', 'ie 8', 'ie 7'], { cascade: true }))
        .pipe(gulp.dest('_site/assets/css'))
        .pipe(browserSync.reload({stream:true}))
        .pipe(gulp.dest('assets/css'));
});

/**
 *  Compile files from _pugFiles into _includes (for future jekyll builds).
 *  They have to be manually included in the index.html file with liquid syntax.
 */
gulp.task('pug', function(){
    return gulp.src('_pugFiles/*.pug')
        .pipe(pug())
        .pipe(gulp.dest('_includes'));
});

gulp.task('responsiveScripts', function() {
  return gulp.src('assets/js/responsive/*.js')
    .pipe(concat('responsive.js'))
    .pipe(uglify())
    .pipe(gulp.dest('_site/assets/js'))
    .pipe(browserSync.reload({stream:true}))
    .pipe(gulp.dest('assets/js'));
});

gulp.task('moduleScripts', function() {
  return gulp.src('assets/js/modules/*.js')
    .pipe(concat('modules.js'))
    .pipe(uglify())
    .pipe(gulp.dest('_site/assets/js'))
    .pipe(browserSync.reload({stream:true}))
    .pipe(gulp.dest('assets/js'));
});

/**
 * Watch scss files for changes & recompile
 * Watch html/md files, run jekyll & reload BrowserSync
 */
gulp.task('watch', function () {
    gulp.watch('assets/css/**', ['sass']);
    gulp.watch('_pugFiles/*.pug', ['pug']);
    gulp.watch('assets/js/**/*.js', ['responsiveScripts', 'moduleScripts']);
    gulp.watch([
        '*.html',
        '_layouts/*.html',
        '_posts/*',
        '_includes/*',  // watch the newly compiled html files.
        '*.json'
    ], ['jekyll-rebuild']);
});

/**
 * Default task, running just `gulp` will compile the sass,
 * compile the jekyll site, launch BrowserSync & watch files.
 */
gulp.task('default', ['browser-sync', 'watch']);
