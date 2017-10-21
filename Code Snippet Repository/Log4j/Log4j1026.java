    private void loadImplementationsInJar(final Test test, final String parent, final File jarFile) {
        JarInputStream jarStream = null;
        try {
            jarStream = new JarInputStream(new FileInputStream(jarFile));
            loadImplementationsInJar(test, parent, jarFile.getPath(), jarStream);
        } catch (final IOException ex) {
            LOGGER.error("Could not search JAR file '{}' for classes matching criteria {}, file not found", jarFile,
                    test, ex);
        } finally {
            close(jarStream, jarFile);
        }
    }
