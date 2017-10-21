    @PluginFactory
    public static PropertiesRewritePolicy createPolicy(@PluginConfiguration final Configuration config,
                                                @PluginElement("Properties") final Property[] props) {
        if (props == null || props.length == 0) {
            LOGGER.error("Properties must be specified for the PropertiesRewritePolicy");
            return null;
        }
        final List<Property> properties = Arrays.asList(props);
        return new PropertiesRewritePolicy(config, properties);
    }
