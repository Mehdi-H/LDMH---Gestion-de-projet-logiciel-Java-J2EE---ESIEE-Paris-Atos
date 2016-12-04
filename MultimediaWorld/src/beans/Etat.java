package beans;

public class Etat 
{
	// ========================================================================
	// == ENUM
	// ========================================================================
	
	public static enum Label 
	{
		CART("Panier"),
		ORDERED("Commandé"),
		DELIVERING("Livraison en cours"),
		DELIVERED("Livré");
		
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
			return Label.CART;
		}
	}
	
	// ========================================================================
	// == ATTRIBUT
	// ========================================================================
	
	private String label;
	
	// ========================================================================
	// == GETTERS & SETTERS
	// ========================================================================

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
