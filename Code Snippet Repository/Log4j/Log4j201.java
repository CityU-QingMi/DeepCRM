    private void loadProvider(final BundleContext context, final BundleWiring bundleWiring) {
        final String filter = "(APIVersion>=2.60)";
        try {
            final Collection<ServiceReference<Provider>> serviceReferences = context.getServiceReferences(Provider.class, filter);
            Provider maxProvider = null;
            for (final ServiceReference<Provider> serviceReference : serviceReferences) {
                final Provider provider = context.getService(serviceReference);
                if (maxProvider == null || provider.getPriority() > maxProvider.getPriority()) {
                    maxProvider = provider;
                }
            }
            if (maxProvider != null) {
                ProviderUtil.addProvider(maxProvider);
            }
        } catch (final InvalidSyntaxException ex) {
            LOGGER.error("Invalid service filter: " + filter, ex);
        }
        final List<URL> urls = bundleWiring.findEntries("META-INF", "log4j-provider.properties", 0);
        for (final URL url : urls) {
            ProviderUtil.loadProvider(url, bundleWiring.getClassLoader());
        }
    }
