    private void loadDocuments(String configFileName) {
        try {
            loadedFileUrls.clear();
            documents = loadConfigurationFiles(configFileName, null);
        } catch (ConfigurationException e) {
            throw e;
        } catch (Exception e) {
            throw new ConfigurationException("Error loading configuration file " + configFileName, e);
        }
    }
