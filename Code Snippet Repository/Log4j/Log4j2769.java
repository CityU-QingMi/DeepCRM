    public LogManager() {
        super();
        AbstractLoggerAdapter adapter = null;
        final String overrideAdaptorClassName =
            PropertiesUtil.getProperties().getStringProperty(Constants.LOGGER_ADAPTOR_PROPERTY);
        if (overrideAdaptorClassName != null) {
            try {
                LOGGER.info("Trying to use LoggerAdaptor [{}] specified by Log4j property.", overrideAdaptorClassName);
                adapter = LoaderUtil.newCheckedInstanceOf(overrideAdaptorClassName, AbstractLoggerAdapter.class);
            } catch (final Exception e) {
                LOGGER.error("Specified LoggerAdapter [{}] is incompatible.", overrideAdaptorClassName, e);
            }
        }
        if (adapter == null) {
            // default adapter
            String adapterClassName;
            try {
                // find out if log4j-core is available
                LoaderUtil.loadClass(Constants.CORE_LOGGER_CLASS_NAME);
                adapterClassName = Constants.CORE_LOGGER_ADAPTER_CLASS_NAME;
            } catch (final ClassNotFoundException ignored) {
                adapterClassName = Constants.API_LOGGER_ADAPTER_CLASS_NAME;
            }
            LOGGER.debug("Attempting to use {}", adapterClassName);
            try {
                adapter = LoaderUtil.newCheckedInstanceOf(adapterClassName, AbstractLoggerAdapter.class);
            } catch (final Exception e) {
                throw LOGGER.throwing(new LoggingException(e));
            }
        }
        loggerAdapter = adapter;
        LOGGER.info("Registered Log4j as the java.util.logging.LogManager.");
    }
