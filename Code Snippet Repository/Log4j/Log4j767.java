    @PluginFactory
    public static MapRewritePolicy createPolicy(
            @PluginAttribute("mode") final String mode,
            @PluginElement("KeyValuePair") final KeyValuePair[] pairs) {
        Mode op = mode == null ? op = Mode.Add : Mode.valueOf(mode);
        if (pairs == null || pairs.length == 0) {
            LOGGER.error("keys and values must be specified for the MapRewritePolicy");
            return null;
        }
        final Map<String, Object> map = new HashMap<>();
        for (final KeyValuePair pair : pairs) {
            final String key = pair.getKey();
            if (key == null) {
                LOGGER.error("A null key is not valid in MapRewritePolicy");
                continue;
            }
            final String value = pair.getValue();
            if (value == null) {
                LOGGER.error("A null value for key " + key + " is not allowed in MapRewritePolicy");
                continue;
            }
            map.put(pair.getKey(), pair.getValue());
        }
        if (map.isEmpty()) {
            LOGGER.error("MapRewritePolicy is not configured with any valid key value pairs");
            return null;
        }
        return new MapRewritePolicy(map, op);
    }
