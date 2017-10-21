    String validateMultiProperty(String key, Object[] meta) {

        int    dbNumber;
        String prefix = (String) meta[indexName];

        if (meta[indexName].equals(sc_key_database)) {
            if (sc_key_database.equals(key)) {
                key = key + ".0";
            }
        }

        try {
            dbNumber = Integer.parseInt(key.substring(prefix.length() + 1));
        } catch (NumberFormatException e1) {
            return ("malformed database enumerator: " + key);
        }

        if (meta[indexName].equals(sc_key_dbname)) {
            String alias    = stringProps.getProperty(key).toLowerCase();
            Object existing = idToAliasMap.put(dbNumber, alias);

            if (existing != null) {
                return "duplicate database enumerator: " + key;
            }
        } else if (meta[indexName].equals(sc_key_database)) {
            String path     = stringProps.getProperty(key);
            Object existing = idToPathMap.put(dbNumber, path);

            if (existing != null) {
                return "duplicate database enumerator: " + key;
            }
        }

        return null;
    }
