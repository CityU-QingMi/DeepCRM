    public boolean shouldCreateIfNew(Class parentClass, String property, Object target, String keyProperty, boolean isIndexAccessed) {
        CreateIfNull annotation = getAnnotation(parentClass, property, CreateIfNull.class);
        if (annotation != null) {
            return annotation.value();
        }
        String configValue = (String) xworkConverter.getConverter(parentClass, CREATE_IF_NULL_PREFIX + property);
        //check if a value is in the config
        if (configValue != null) {
            return BooleanUtils.toBoolean(configValue);
        }

        //default values depend on target type
        //and whether this is accessed by an index
        //in the case of List
        return (target instanceof Map) || isIndexAccessed;
    }
