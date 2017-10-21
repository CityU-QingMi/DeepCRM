    @Override
    public String getProperty(String key)
    {
        Prop prop = props.getProp(key,false);
        if (prop == null)
        {
            return null;
        }
        return prop.value;
    }
