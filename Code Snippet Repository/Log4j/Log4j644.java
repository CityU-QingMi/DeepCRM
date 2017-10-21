    @PluginFactory
    public static String[] createFailovers(@PluginElement("AppenderRef") final AppenderRef... refs) {

        if (refs == null) {
            LOGGER.error("failovers must contain an appender reference");
            return null;
        }
        final String[] arr = new String[refs.length];
        for (int i = 0; i < refs.length; ++i) {
            arr[i] = refs[i].getRef();
        }
        return arr;
    }
