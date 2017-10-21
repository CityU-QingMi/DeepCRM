    @Override
    public void addDefaultResourceBundle(String resourceBundleName) {
        //make sure this doesn't get added more than once
        final ClassLoader ccl = getCurrentThreadContextClassLoader();
        synchronized (XWORK_MESSAGES_BUNDLE) {
            List<String> bundles = classLoaderMap.get(ccl.hashCode());
            if (bundles == null) {
                bundles = new CopyOnWriteArrayList<>();
                classLoaderMap.put(ccl.hashCode(), bundles);
            }
            bundles.remove(resourceBundleName);
            bundles.add(0, resourceBundleName);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Added default resource bundle '{}' to default resource bundles for the following classloader '{}'", resourceBundleName, ccl.toString());
        }
    }
