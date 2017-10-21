    String validateSystemProperty(String key, Object[] meta) {

        String prefix      = (String) meta[indexName];
        String specificKey = key.substring(prefix.length() + 1);
        String value       = stringProps.getProperty(key);

        if (value == null) {
            return "value required for property: " + key;
        }

        System.setProperty(specificKey, value);

        return null;
    }
