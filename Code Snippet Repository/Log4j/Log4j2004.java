    @BeforeClass
    public static void setupClass() {
        final File file = new File(STATUS_LOG);
        file.delete();
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY, CONFIG);
        final LoggerContext ctx = LoggerContext.getContext();
        final Configuration config = ctx.getConfiguration();
        if (config instanceof XmlConfiguration) {
            final String name = config.getName();
            if (name == null || !name.equals("XMLConfigTest")) {
                ctx.reconfigure();
            }
        } else {
            ctx.reconfigure();
        }
    }
