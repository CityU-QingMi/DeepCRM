    public void expandSystemProperties() throws IOException
    {
        StartLog.debug("Expanding System Properties");
        
        for (String key : systemPropertyKeys)
        {
            String value = properties.getString(key);
            if (value!=null)
            {
                String expanded = properties.expand(value);
                if (!value.equals(expanded))
                    System.setProperty(key,expanded);
            }
        }
    }
