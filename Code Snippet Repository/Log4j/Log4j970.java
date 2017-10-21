    @PluginFactory
    public static StrLookup configureSubstitutor(@PluginElement("Properties") final Property[] properties,
                                                 @PluginConfiguration final Configuration config) {
        if (properties == null) {
            return new Interpolator(config.getProperties());
        }
        final Map<String, String> map = new HashMap<>(config.getProperties());

        for (final Property prop : properties) {
            map.put(prop.getName(), prop.getValue());
        }

        return new Interpolator(new MapLookup(map), config.getPluginPackages());
    }
