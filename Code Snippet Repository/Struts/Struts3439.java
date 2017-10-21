    @Override
    public void init(ServletContext servletContext) {
        this.servletContext = servletContext;

        installManagedBundles();

        addSpringOSGiSupport();

        // add the bundle context to the ServletContext
        servletContext.setAttribute(OSGI_BUNDLE_CONTEXT, bctx);
    }
