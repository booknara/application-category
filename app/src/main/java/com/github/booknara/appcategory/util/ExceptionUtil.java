package com.github.booknara.appcategory.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	// Suppress default constructor for noninstantiability
    private ExceptionUtil() { }    // This constructor will never be invoked
    
	static final String CNAME = ExceptionUtil.class.getSimpleName();
	
	/**
	 * Get exception call stack information
	 * 
	 * @param e
	 * @return
	 */
	public static String exception(Exception e) {
		StringWriter writer = new StringWriter();
		String rvalue = "";
		
		try {	
			e.printStackTrace(new PrintWriter(writer));
			rvalue = writer.toString();
			return rvalue.substring(0, rvalue.length() -1);
		} catch(Exception ex) {
			// ignore
		}
		
		return rvalue;
	}

    public static String error(Error e) {
        StringWriter writer = new StringWriter();
        String rvalue = "";

        try {
            e.printStackTrace(new PrintWriter(writer));
            rvalue = writer.toString();
            return rvalue.substring(0, rvalue.length() -1);
        } catch(Exception ex) {
            // ignore
        }

        return rvalue;
    }
}
