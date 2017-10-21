    public BuiltConfiguration(final LoggerContext loggerContext, final ConfigurationSource source, final Component rootComponent) {
        super(loggerContext, source);
        statusConfig = new StatusConfiguration().withVerboseClasses(VERBOSE_CLASSES).withStatus(getDefaultStatus());
        for (final Component component : rootComponent.getComponents()) {
            switch (component.getPluginType()) {
                case "Scripts": {
                    scriptsComponent = component;
                    break;
                }
                case "Loggers": {
                    loggersComponent = component;
                    break;
                }
                case "Appenders": {
                    appendersComponent = component;
                    break;
                }
                case "Filters": {
                    filtersComponent = component;
                    break;
                }
                case "Properties": {
                    propertiesComponent = component;
                    break;
                }
                case "CustomLevels": {
                    customLevelsComponent = component;
                    break;
                }
            }
        }
        this.rootComponent = rootComponent;
    }
