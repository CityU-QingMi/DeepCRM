    public void initialize() throws InitializationException {
        
        Map<String, RuntimeConfigProperty> props;
        try {
            // retrieve properties from database
            props = this.getProperties();

            // if any default props missing from the properties DB table,
            // initialize them and save them to that table.
            initializeMissingProps(props);
            this.saveProperties(props);

        } catch (Exception e) {
            log.fatal("Failed to initialize runtime configuration properties."+
                    "Please check that the database has been upgraded!", e);
            throw new RuntimeException(e);
        }
        
    }
