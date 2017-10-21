    @Deprecated
    public Map<Property, Boolean> getProperties() {
        if (properties == null) {
            return null;
        }
        if (propertiesMap == null) { // lazily initialize: only used by user custom code, not by Log4j any more
            final Map<Property, Boolean> result = new HashMap<>(properties.size() * 2);
            for (int i = 0; i < properties.size(); i++) {
                result.put(properties.get(i), Boolean.valueOf(properties.get(i).isValueNeedsLookup()));
            }
            propertiesMap = Collections.unmodifiableMap(result);
        }
        return propertiesMap;
    }
