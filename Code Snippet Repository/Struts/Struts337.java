    private Object getKey(Map context, Object name) {
        Class lastClass = (Class) context.get(XWorkConverter.LAST_BEAN_CLASS_ACCESSED);
        String lastProperty = (String) context.get(XWorkConverter.LAST_BEAN_PROPERTY_ACCESSED);
        if (lastClass == null || lastProperty == null) {
            // return java.lang.String.class;
            // commented out the above -- it makes absolutely no sense for when setting basic maps!
            return name;
        }
        Class keyClass = objectTypeDeterminer.getKeyClass(lastClass, lastProperty);
        if (keyClass == null) {
            keyClass = java.lang.String.class;
        }

        return xworkConverter.convertValue(context, name, keyClass);
    }
