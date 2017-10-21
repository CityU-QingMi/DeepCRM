    TypeConverter lookupSuper(Class clazz) {
        TypeConverter result = null;

        if (clazz != null) {
            result = converterHolder.getDefaultMapping(clazz.getName());

            if (result == null) {
                // Looks for direct interfaces (depth = 1 )
                Class[] interfaces = clazz.getInterfaces();

                for (Class anInterface : interfaces) {
                    if (converterHolder.containsDefaultMapping(anInterface.getName())) {
                        result = converterHolder.getDefaultMapping(anInterface.getName());
                        break;
                    }
                }

                if (result == null) {
                    // Looks for the superclass
                    // If 'clazz' is the Object class, an interface, a primitive type or void then clazz.getSuperClass() returns null
                    result = lookupSuper(clazz.getSuperclass());
                }
            }
        }

        return result;
    }
