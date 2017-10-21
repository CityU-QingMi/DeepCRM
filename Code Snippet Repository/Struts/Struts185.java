    public Class getElementClass(Class parentClass, String property, Object key) {
        Element annotation = getAnnotation(parentClass, property, Element.class);
        if (annotation != null) {
            return annotation.value();
        }
        Class clazz = getClass(parentClass, property, true);
        if (clazz != null) {
            return clazz;
        }
        clazz = (Class) xworkConverter.getConverter(parentClass, ELEMENT_PREFIX + property);
        if (clazz == null) {
            clazz = (Class) xworkConverter.getConverter(parentClass, DEPRECATED_ELEMENT_PREFIX + property);
            if (clazz != null) {
                LOG.info("The Collection_xxx pattern for collection type conversion is deprecated. Please use Element_xxx!");
            }
        }
        return clazz;
    }
