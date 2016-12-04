package daos;

public interface ArtisteDao 
{
	int create(final String nom_artiste);
	void delete(final int id_artiste);
}
