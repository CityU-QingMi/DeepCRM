    @PluginFactory
    public static CustomLevelConfig createLevel(// @formatter:off
            @PluginAttribute("name") final String levelName,
            @PluginAttribute("intLevel") final int intLevel) {
        // @formatter:on

        StatusLogger.getLogger().debug("Creating CustomLevel(name='{}', intValue={})", levelName, intLevel);
        Level.forName(levelName, intLevel);
        return new CustomLevelConfig(levelName, intLevel);
    }
