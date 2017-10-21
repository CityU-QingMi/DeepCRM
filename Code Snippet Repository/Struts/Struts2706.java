    public ClassLoader getJspLoader() {
        if( jspLoader == null ) {
            jspLoader = new JasperLoader
            (new URL[] {baseUrl},
                    getClassLoader(),
                    rctxt.getPermissionCollection(),
                    rctxt.getCodeSource());
        }
        return jspLoader;
    }
