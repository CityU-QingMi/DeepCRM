    public Class getKeyClass(Class parentClass, String property) {
        Key annotation = getAnnotation(parentClass, property, Key.class);
        if (annotation != null) {
            return annotation.value();
        }
        Class clazz = getClass(parentClass, property, false);
        if (clazz != null) {
            return clazz;
        }
        return (Class) xworkConverter.getConverter(parentClass, KEY_PREFIX + property);
    }
