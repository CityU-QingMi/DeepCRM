    private String getOption(Map<String, ?> options, String key, String defaultValue)
    {
        Object value = options.get(key);

        if (value == null)
        {
            return defaultValue;
        }

        return (String)value;
    }
