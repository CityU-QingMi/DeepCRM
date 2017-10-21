    public void setSystemProperties(SystemProperties systemProperties)
    {
        if (this.systemProperties == null)
            this.systemProperties = systemProperties;
        else
        {
            for (SystemProperty prop: systemProperties.getSystemProperties())
            {
                this.systemProperties.setSystemProperty(prop);
            }   
        }
    }
