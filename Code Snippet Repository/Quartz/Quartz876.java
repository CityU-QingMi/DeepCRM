    public void setPropertiesFile(String propertiesFile) {
        if (useProperties) {
            log
                    .error("Must specify only one of 'Properties' or 'PropertiesFile'");

            error = true;

            return;
        }

        usePropertiesFile = true;

        this.propertiesFile = propertiesFile;
    }
