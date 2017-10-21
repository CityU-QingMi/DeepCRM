    private void ensureSystemPropertySet(String key)
    {
        if (systemPropertyKeys.contains(key))
        {
            return; // done
        }

        if (properties.containsKey(key))
        {
            String val = properties.expand(properties.getString(key));
            if (val == null)
            {
                return; // no value to set
            }
            // setup system property
            systemPropertyKeys.add(key);
            System.setProperty(key,val);
        }
    }
