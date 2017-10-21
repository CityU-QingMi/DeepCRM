    @Inject
    public DefaultBeanValidationManager(
            @Inject(value = ValidatorConstants.PROVIDER_CLASS, required = false) String providerClassName,
            @Inject(value = ValidatorConstants.IGNORE_XMLCONFIGURAITION, required = false) String ignoreXMLConfiguration,
            @Inject(required = true) ObjectFactory objectFactory) {
        super();
        LOG.info("Initializing bean validation factory to get a validator");

        if (StringUtils.isNotBlank(providerClassName)) {
            try {
                this.providerClass = objectFactory.getClassInstance(providerClassName);
                LOG.info("{} validator found", this.providerClass.getName());
            } catch (ClassNotFoundException e) {
                LOG.error("Unable to find any bean validator implementation for class: {}", providerClassName);
                LOG.error("Unable to load bean validation provider class", e);
            }

        }
        if (this.providerClass == null) {
            LOG.warn("********** No bean validator class defined - Falling back to default provider **********");
        }

        Configuration configuration =
                this.providerClass != null
                        ? Validation.byProvider(this.providerClass).configure()
                        : Validation.byDefaultProvider().configure();
        if (BooleanUtils.toBoolean(ignoreXMLConfiguration)) {
            configuration.ignoreXmlConfiguration();
            LOG.info("XML configurations will be ignored by Validator, to enable XML based validation, set struts.beanValidation.ignoreXMLConfiguration to false.");
        }
        if (configuration != null) {
            this.validationFactory = configuration.buildValidatorFactory();
        }

    }
