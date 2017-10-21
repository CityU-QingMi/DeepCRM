    @PluginFactory
    public static LoggerFields createLoggerFields(
        @PluginElement("LoggerFields") final KeyValuePair[] keyValuePairs,
        @PluginAttribute("sdId") final String sdId,
        @PluginAttribute("enterpriseId") final String enterpriseId,
        @PluginAttribute(value = "discardIfAllFieldsAreEmpty") final boolean discardIfAllFieldsAreEmpty) {
        final Map<String, String> map = new HashMap<>();

        for (final KeyValuePair keyValuePair : keyValuePairs) {
            map.put(keyValuePair.getKey(), keyValuePair.getValue());
        }

        return new LoggerFields(map, sdId, enterpriseId, discardIfAllFieldsAreEmpty);
    }
