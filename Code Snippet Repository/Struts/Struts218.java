    protected Map<String, Object> buildConverterMapping(Class clazz) throws Exception {
        Map<String, Object> mapping = new HashMap<>();

        // check for conversion mapping associated with super classes and any implemented interfaces
        Class curClazz = clazz;

        while (!curClazz.equals(Object.class)) {
            // add current class' mappings
            addConverterMapping(mapping, curClazz);

            // check interfaces' mappings
            Class[] interfaces = curClazz.getInterfaces();

            for (Class anInterface : interfaces) {
                addConverterMapping(mapping, anInterface);
            }

            curClazz = curClazz.getSuperclass();
        }

        if (mapping.size() > 0) {
            converterHolder.addMapping(clazz, mapping);
        } else {
            converterHolder.addNoMapping(clazz);
        }

        return mapping;
    }
