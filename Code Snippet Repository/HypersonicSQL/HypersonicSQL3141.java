    private IntKeyHashMap getDBNameArray() {

        final String  prefix       = ServerProperties.sc_key_dbname + ".";
        final int     prefixLen    = prefix.length();
        IntKeyHashMap idToAliasMap = new IntKeyHashMap();
        Enumeration   en           = serverProperties.propertyNames();

        for (; en.hasMoreElements(); ) {
            String key = (String) en.nextElement();

            if (!key.startsWith(prefix)) {
                continue;
            }

            int dbNumber;

            try {
                dbNumber = Integer.parseInt(key.substring(prefixLen));
            } catch (NumberFormatException e1) {
                printWithThread("malformed database enumerator: " + key);

                continue;
            }

            String alias = serverProperties.getProperty(key).toLowerCase();

            if (!aliasSet.add(alias)) {
                printWithThread("duplicate alias: " + alias);
            }

            Object existing = idToAliasMap.put(dbNumber, alias);

            if (existing != null) {
                printWithThread("duplicate database enumerator: " + key);
            }
        }

        return idToAliasMap;
    }
