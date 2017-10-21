    private String getProperty(Props props, String key, String defVal)
    {
        String val = props.getString(key,defVal);
        if (val == null)
        {
            return defVal;
        }
        val = val.trim();
        if (val.length() <= 0)
        {
            return defVal;
        }
        return val;
    }
