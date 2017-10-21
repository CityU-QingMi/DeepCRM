    public void setProperties(String properties) {
        if (usePropertiesFile) {
            log
                    .error("Must specify only one of 'Properties' or 'PropertiesFile'");

            error = true;

            return;
        }

        useProperties = true;

        try {
            properties = properties.replace(File.separator, "/");
            ByteArrayInputStream bais = new ByteArrayInputStream(properties
                    .getBytes());
            this.properties = new Properties();
            this.properties.load(bais);
        } catch (IOException ioe) {
            // should not happen
        }
    }
