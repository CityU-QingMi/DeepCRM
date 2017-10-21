    public Prop getProp(String key, boolean searchSystemProps)
    {
        Prop prop = props.get(key);
        if ((prop == null) && searchSystemProps)
        {
            // try system property
            prop = getSystemProperty(key);
        }
        return prop;
    }
