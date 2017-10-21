    public PropertiesUtil(final String propertiesFileName) {
        final Properties properties = new Properties();
        for (final URL url : LoaderUtil.findResources(propertiesFileName)) {
            try (final InputStream in = url.openStream()) {
                properties.load(in);
            } catch (final IOException ioe) {
                LowLevelLogUtil.logException("Unable to read " + url.toString(), ioe);
            }
        }
        this.props = properties;
    }
