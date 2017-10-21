    @Override
    public void start(final BundleContext context) throws Exception {
        ProviderUtil.STARTUP_LOCK.lock();
        lockingProviderUtil = true;
        final BundleWiring self = context.getBundle().adapt(BundleWiring.class);
        final List<BundleWire> required = self.getRequiredWires(LoggerContextFactory.class.getName());
        for (final BundleWire wire : required) {
            loadProvider(context, wire.getProviderWiring());
        }
        context.addBundleListener(this);
        final Bundle[] bundles = context.getBundles();
        for (final Bundle bundle : bundles) {
            loadProvider(bundle);
        }
        unlockIfReady();
    }
