    protected void addIfMatching(final Test test, final String fqn) {
        try {
            final ClassLoader loader = getClassLoader();
            if (test.doesMatchClass()) {
                final String externalName = fqn.substring(0, fqn.indexOf('.')).replace('/', '.');
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Checking to see if class {} matches criteria {}", externalName, test);
                }

                final Class<?> type = loader.loadClass(externalName);
                if (test.matches(type)) {
                    classMatches.add(type);
                }
            }
            if (test.doesMatchResource()) {
                URL url = loader.getResource(fqn);
                if (url == null) {
                    url = loader.getResource(fqn.substring(1));
                }
                if (url != null && test.matches(url.toURI())) {
                    resourceMatches.add(url.toURI());
                }
            }
        } catch (final Throwable t) {
            LOGGER.warn("Could not examine class {}", fqn, t);
        }
    }
