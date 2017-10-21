    @Inject
    public CdiObjectFactory(Container container) {
        super(container);
        LOG.info("Initializing Struts2 CDI integration...");
        this.beanManager = findBeanManager();
        if (beanManager != null) {
            LOG.info("Struts2 CDI integration initialized.");
        } else {
            LOG.error("Struts2 CDI integration could not be initialized.");
        }
    }
