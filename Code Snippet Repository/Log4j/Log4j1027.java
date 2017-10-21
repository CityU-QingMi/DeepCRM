    private void loadImplementationsInJar(final Test test, final String parent, final String path,
            final JarInputStream stream) {

        try {
            JarEntry entry;

            while ((entry = stream.getNextJarEntry()) != null) {
                final String name = entry.getName();
                if (!entry.isDirectory() && name.startsWith(parent) && isTestApplicable(test, name)) {
                    addIfMatching(test, name);
                }
            }
        } catch (final IOException ioe) {
            LOGGER.error("Could not search JAR file '{}' for classes matching criteria {} due to an IOException", path,
                    test, ioe);
        }
    }
