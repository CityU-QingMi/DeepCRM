    private AnnotationActionValidatorManager createValidationManager(final Class<? extends ActionSupport> actionClass, Locale locale) throws Exception {
        loadConfigurationProviders(new ConfigurationProvider() {
            public void destroy() {

            }

            public void init(Configuration configuration) throws ConfigurationException {
                configuration.addPackageConfig("default", new PackageConfig.Builder("default")
                        .addActionConfig("annotation", new ActionConfig.Builder("", "annotation", actionClass.getName()).build())
                        .build());
            }

            public boolean needsReload() {
                return false;
            }

            public void loadPackages() throws ConfigurationException {

            }

            public void register(ContainerBuilder builder, LocatableProperties props) throws ConfigurationException {
                builder.constant(XWorkConstants.DEV_MODE, true);
            }
        });

        // ActionContext is destroyed during rebuilding configuration
        ActionContext.getContext().setLocale(locale);

        ActionInvocation invocation = new DefaultActionInvocation(ActionContext.getContext().getContextMap(), true);
        container.inject(invocation);
        invocation.init(actionProxyFactory.createActionProxy("", "annotation", null, ActionContext.getContext().getContextMap()));

        AnnotationActionValidatorManager manager = new AnnotationActionValidatorManager();
        container.inject(manager);

        ValidatorFactory vf = container.getInstance(ValidatorFactory.class);
        vf.registerValidator("myValidator", MyValidator.class.getName());

        return manager;
    }
