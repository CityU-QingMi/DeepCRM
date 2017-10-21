    @Inject
    public PackageBasedActionConfigBuilder(Configuration configuration, Container container, ObjectFactory objectFactory,
                                           @Inject(ConventionConstants.CONVENTION_REDIRECT_TO_SLASH) String redirectToSlash,
                                           @Inject(ConventionConstants.CONVENTION_DEFAULT_PARENT_PACKAGE) String defaultParentPackage) {

        // Validate that the parameters are okay
        this.configuration = configuration;
        this.actionNameBuilder = container.getInstance(ActionNameBuilder.class, container.getInstance(String.class, ConventionConstants.CONVENTION_ACTION_NAME_BUILDER));
        this.resultMapBuilder = container.getInstance(ResultMapBuilder.class, container.getInstance(String.class, ConventionConstants.CONVENTION_RESULT_MAP_BUILDER));
        this.interceptorMapBuilder = container.getInstance(InterceptorMapBuilder.class, container.getInstance(String.class, ConventionConstants.CONVENTION_INTERCEPTOR_MAP_BUILDER));
        this.objectFactory = objectFactory;
        this.redirectToSlash = Boolean.parseBoolean(redirectToSlash);

        if (LOG.isTraceEnabled()) {
            LOG.trace("Setting action default parent package to [{}]", defaultParentPackage);
        }

        this.defaultParentPackage = defaultParentPackage;
    }
