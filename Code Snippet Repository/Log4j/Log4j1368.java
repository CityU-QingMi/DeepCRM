    @Override
    public void start(final BundleContext context) throws Exception {
        final Provider provider = new Log4jProvider();
        final Hashtable<String, String> props = new Hashtable<>();
        props.put("APIVersion", "2.60");
        provideRegistration = context.registerService(Provider.class.getName(), provider, props);
        // allow the user to override the default ContextSelector (e.g., by using BasicContextSelector for a global cfg)
        if (PropertiesUtil.getProperties().getStringProperty(Constants.LOG4J_CONTEXT_SELECTOR) == null) {
            System.setProperty(Constants.LOG4J_CONTEXT_SELECTOR, BundleContextSelector.class.getName());
        }
        if (this.contextRef.compareAndSet(null, context)) {
            context.addBundleListener(this);
            // done after the BundleListener as to not miss any new bundle installs in the interim
            scanInstalledBundlesForPlugins(context);
        }
    }
