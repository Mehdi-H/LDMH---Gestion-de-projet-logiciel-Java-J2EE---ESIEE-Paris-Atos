package beans;

public class Role 
{
	// ========================================================================
	// == ENUM
	// ========================================================================
	
	public static enum Label 
	{
		ADMIN("admin"),
		PROFESSIONAL("professionnel"),
		PRIVATE("particulier"),
		VISITOR("visiteur");
		
		private String name = "";
		
		Label(final String name)
		{
			this.name= name;
		}
		
		public String toString()
		{
			return this.name;
		}
		
		public static Label getDefault()
		{
			return Label.VISITOR;
		}
	}
	
	// ========================================================================
	// == ATTRIBUT
	// ========================================================================
	
	private Label label;
	
	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================

	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
}
