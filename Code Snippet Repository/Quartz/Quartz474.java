    public static void setBeanProps(Object obj, String[] propNames, Object[] propValues)  throws JobPersistenceException {
        
        if(propNames == null || propNames.length == 0)
            return;
        if(propNames.length != propValues.length)
            throw new IllegalArgumentException("propNames[].lenght != propValues[].length");
        
        String name = null;
        
        try {
            BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propDescs = bi.getPropertyDescriptors();
        
            for(int i=0; i < propNames.length; i++) {
                name = propNames[i];
                String c = name.substring(0, 1).toUpperCase(Locale.US);
                String methName = "set" + c + name.substring(1);
        
                java.lang.reflect.Method setMeth = getSetMethod(methName, propDescs);
        
                if (setMeth == null) {
                    throw new NoSuchMethodException(
                            "No setter for property '" + name + "'");
                }
    
                Class<?>[] params = setMeth.getParameterTypes();
                if (params.length != 1) {
                    throw new NoSuchMethodException(
                        "No 1-argument setter for property '" + name + "'");
                }
                
                setMeth.invoke(obj, new Object[]{ propValues[i] });
            }
        }
        catch(Exception e) {
            throw new JobPersistenceException(
                "Unable to set property named: " + name +" of object of type: " + obj.getClass().getCanonicalName(), 
                e); 
        }
    }
