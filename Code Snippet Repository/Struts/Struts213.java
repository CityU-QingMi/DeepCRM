    public TypeConverter lookup(String className, boolean isPrimitive) {
        if (converterHolder.containsUnknownMapping(className) && !converterHolder.containsDefaultMapping(className)) {
            return null;
        }

        TypeConverter result = converterHolder.getDefaultMapping(className);

        //Looks for super classes
        if (result == null && !isPrimitive) {
            Class clazz = null;

            try {
                clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
            } catch (ClassNotFoundException cnfe) {
                LOG.debug("Cannot load class {}", className, cnfe);
            }

            result = lookupSuper(clazz);

            if (result != null) {
                //Register now, the next lookup will be faster
                registerConverter(className, result);
            } else {
                // if it isn't found, never look again (also faster)
                registerConverterNotFound(className);
            }
        }

        return result;
    }
