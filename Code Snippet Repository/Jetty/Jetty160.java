    private void setSystemProperties()
    {
        if (systemProperties != null)
        {
            Iterator propertiesIterator = systemProperties.getSystemProperties().iterator();
            while (propertiesIterator.hasNext())
            {
                Property property = ((Property) propertiesIterator.next());
                SystemProperties.setIfNotSetAlready(property);
            }
        }
    }
