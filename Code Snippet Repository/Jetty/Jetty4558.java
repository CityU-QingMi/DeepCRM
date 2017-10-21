    protected Property processProperty(String key,String value,String source, Function<String, String> getter)
    {
        if (key.endsWith("+"))
        {
            key = key.substring(0,key.length() - 1);
            String orig = getter.apply(key);
            if (orig == null || orig.isEmpty())
            {
                if (value.startsWith(","))
                    value = value.substring(1);
            }
            else
            {
                value = orig + value;
                source = propertySource.get(key) + "," + source;
            }
        }
        if (key.endsWith("?"))
        {
            key = key.substring(0,key.length() - 1);
            String preset = getter.apply(key);
            if (preset!=null)
            {
                source = source+"?=";
                value = preset;
            }
        }
        else if (propertySource.containsKey(key))
        {
            if (!propertySource.get(key).endsWith("[ini]"))
                StartLog.warn("Property %s in %s already set in %s",key,source,propertySource.get(key));
            propertySource.put(key,source);
        }
        
        return new Property(key,value,source);
    }
