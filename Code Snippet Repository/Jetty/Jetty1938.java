    public void setSystemPropertiesFile(File file) throws Exception
    {
        this.systemPropertiesFile = file;
        Properties properties = new Properties();
        try (InputStream propFile = new FileInputStream(systemPropertiesFile))
        {
            properties.load(propFile);
        }
        if (this.systemProperties == null )
            this.systemProperties = new SystemProperties();
        
        for (Enumeration<?> keys = properties.keys(); keys.hasMoreElements();  )
        {
            String key = (String)keys.nextElement();
            if ( ! systemProperties.containsSystemProperty(key) )
            {
                SystemProperty prop = new SystemProperty();
                prop.setKey(key);
                prop.setValue(properties.getProperty(key));
                
                this.systemProperties.setSystemProperty(prop);
            }
        } 
    }
