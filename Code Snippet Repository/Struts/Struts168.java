    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof XmlConfigurationProvider)) {
            return false;
        }

        final XmlConfigurationProvider xmlConfigurationProvider = (XmlConfigurationProvider) o;

        if ((configFileName != null) ? (!configFileName.equals(xmlConfigurationProvider.configFileName)) : (xmlConfigurationProvider.configFileName != null)) {
            return false;
        }

        return true;
    }
