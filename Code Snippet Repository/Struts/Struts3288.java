    public static String serialize(Object object, Collection<Pattern> excludeProperties,
                                   Collection<Pattern> includeProperties, boolean ignoreHierarchy, boolean enumAsBean,
                                   boolean excludeNullProperties, String defaultDateFormat, boolean cacheBeanInfo) throws JSONException {
        JSONWriter writer = new JSONWriter();
        writer.setIgnoreHierarchy(ignoreHierarchy);
        writer.setEnumAsBean(enumAsBean);
        writer.setDateFormatter(defaultDateFormat);
        writer.setCacheBeanInfo(cacheBeanInfo);
        return writer.write(object, excludeProperties, includeProperties, excludeNullProperties);
    }
