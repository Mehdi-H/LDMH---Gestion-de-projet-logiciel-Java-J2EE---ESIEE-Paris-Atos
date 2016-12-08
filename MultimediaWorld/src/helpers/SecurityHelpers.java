package helpers;

import java.security.MessageDigest;

public abstract class SecurityHelpers 
{
	// ========================================================================
	// == HASH 
	// ========================================================================
	
	public static String sha256(final String src)
	{
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hash = digest.digest(src.getBytes("UTF-8"));
			
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) 
			{
				String hex = Integer.toHexString(0xff & hash[i]);
				
				if(hex.length() == 1) {
					hexString.append('0');
				}
				
				hexString.append(hex);
			}
	
			return hexString.toString();
		} 
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
