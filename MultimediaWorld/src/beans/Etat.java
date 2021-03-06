package beans;

public class Etat 
{
	// ========================================================================
	// == ENUM
	// ========================================================================
	
	public static enum Label 
	{
		CART("Panier"),
		ORDERED("Command�"),
		DELIVERING("Livraison en cours"),
		DELIVERED("Livr�");
		
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
		
		public static Label fromString(final String text)
		{
			if (text != null) {
				for (Label lab : Label.values()) {
					if (text.equalsIgnoreCase(lab.toString())) {
						return lab;
					}
				}
			}
			
			return null;
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
