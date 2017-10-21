    public static AutoProvision getAutoProvision() {        
        String clazzName = WebloggerConfig.getProperty("users.ldap.autoProvision.className");
        
        if (null == clazzName) {
            return null;
        }
        
        Class clazz;
        try {
            clazz = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            log.warn("Unable to found specified Auto Provision class.", e);
            return null;
        }
        
        Class[] interfaces = clazz.getInterfaces();
        for (Class clazz2 : interfaces) {
            if (clazz2.equals(AutoProvision.class)) {
                try {
                    return (AutoProvision) clazz.newInstance();
                } catch (InstantiationException e) {
                    log.warn("InstantiationException while creating: " + clazzName, e);
                } catch (IllegalAccessException e) {
                    log.warn("IllegalAccessException while creating: " + clazzName, e);
                }
            }
        }        
        return null;        
    }   
