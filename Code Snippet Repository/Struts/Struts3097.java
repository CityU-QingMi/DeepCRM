    public Class loadClass(final String name, boolean resolve)
        throws ClassNotFoundException {

        Class clazz = null;                
                                           
        // (0) Check our previously loaded class cache
        clazz = findLoadedClass(name);     
        if (clazz != null) {               
            if (resolve)                   
                resolveClass(clazz);       
            return (clazz);        
        }                          
                          
        // (.5) Permission to access this class when using a SecurityManager
        if (securityManager != null) {     
            int dot = name.lastIndexOf('.');
            if (dot >= 0) {                
                try {        
                    // Do not call the security manager since by default, we grant that package.
                    if (!"org.apache.struts2.jasper.runtime".equalsIgnoreCase(name.substring(0,dot))){
                        securityManager.checkPackageAccess(name.substring(0,dot));
                    }
                } catch (SecurityException se) {
                    String error = "Security Violation, attempt to use " +
                        "Restricted Class: " + name;
                    se.printStackTrace();
                    throw new ClassNotFoundException(error);
                }                          
            }                              
        }

        if( !name.startsWith(Constants.JSP_PACKAGE_NAME + '.') ) {
            // Class is not in org.apache.jsp, therefore, have our
            // parent load it
            clazz = parent.loadClass(name);            
	    if( resolve )
		resolveClass(clazz);
	    return clazz;
	}

	return findClass(name);
    }
