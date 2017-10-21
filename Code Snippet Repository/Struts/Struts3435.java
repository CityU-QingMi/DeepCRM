    protected Properties getProperties(String fileName) {
        ResourceFinder finder = new ResourceFinder("");
        try {
            return finder.findProperties(fileName);
        } catch (IOException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("Unable to read property file [#]", fileName);
            }
            return new Properties();
        }
    }
