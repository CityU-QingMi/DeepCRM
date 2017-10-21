    public String getString(String key)
    {
        if (key == null)
        {
            throw new PropsException("Cannot get value for null key");
        }

        String name = cleanReference(key);

        if (name.length() == 0)
        {
            throw new PropsException("Cannot get value for empty key");
        }

        Prop prop = getProp(name);
        if (prop == null)
        {
            return null;
        }
        return prop.value;
    }
