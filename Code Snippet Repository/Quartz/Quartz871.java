    public QuartzService() {
        // flag initialization errors
        error = false;

        // use PropertiesFile attribute
        usePropertiesFile = false;
        propertiesFile = "";

        // use Properties attribute
        useProperties = false;
        properties = new Properties();

        // default JNDI name for Scheduler
        jndiName = "Quartz";
    }
