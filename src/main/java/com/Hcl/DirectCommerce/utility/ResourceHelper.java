package com.Hcl.DirectCommerce.utility;

/**
 * 
 * @author sujit jena
 *
 */
public class ResourceHelper {

	public static String getResourcePath(String path) {
        String basePath = System.getProperty("user.dir");
        System.out.println(basePath +"\\"+ path);
		return basePath +"\\"+ path;
	}
}
