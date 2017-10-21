    private static java.lang.reflect.Method getSetMethod(String name, PropertyDescriptor[] props) {
        for (int i = 0; i < props.length; i++) {
            java.lang.reflect.Method wMeth = props[i].getWriteMethod();
    
            if (wMeth != null && wMeth.getName().equals(name)) {
                return wMeth;
            }
        }
    
        return null;
    }
