    public void setProperty(String key, String value, String origin)
    {
        Prop prop = props.get(key);
        if (prop == null)
        {
            prop = new Prop(key,value,origin);
        }
        else
        {
            prop = new Prop(key,value,origin,prop);
        }
        props.put(key,prop);
    }
