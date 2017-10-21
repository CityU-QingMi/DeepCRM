    @Inject
    public ConventionUnknownHandler(Configuration configuration, ObjectFactory objectFactory,
                                    ServletContext servletContext, Container container,
                                    @Inject(ConventionConstants.CONVENTION_DEFAULT_PARENT_PACKAGE) String defaultParentPackageName,
                                    @Inject(ConventionConstants.CONVENTION_REDIRECT_TO_SLASH) String redirectToSlash,
                                    @Inject(ConventionConstants.CONVENTION_ACTION_NAME_SEPARATOR) String nameSeparator) {
        this.configuration = configuration;
        this.objectFactory = objectFactory;
        this.servletContext = servletContext;
        this.resultMapBuilder = container.getInstance(ResultMapBuilder.class, container.getInstance(String.class, ConventionConstants.CONVENTION_RESULT_MAP_BUILDER));
        this.conventionsService = container.getInstance(ConventionsService.class, container.getInstance(String.class, ConventionConstants.CONVENTION_CONVENTIONS_SERVICE));
        this.defaultParentPackageName = defaultParentPackageName;
        this.nameSeparator = nameSeparator;

        this.parentPackage = configuration.getPackageConfig(defaultParentPackageName);
        if (parentPackage == null) {
            throw new ConfigurationException("Unknown default parent package [" + defaultParentPackageName + "]");
        }

        this.redirectToSlash = Boolean.parseBoolean(redirectToSlash);

        allowedMethods = TextParseUtil.commaDelimitedStringToSet("execute,input,back,cancel,browse,index");
    }
