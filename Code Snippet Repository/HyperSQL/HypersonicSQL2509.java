    void filterLoadedProperties() {

        String val = stringProps.getProperty(sql_enforce_strict_size);

        if (val != null) {
            stringProps.setProperty(sql_enforce_size, val);
        }

        Enumeration en = stringProps.propertyNames();

        while (en.hasMoreElements()) {
            String  key    = (String) en.nextElement();
            boolean accept = dbMeta.containsKey(key);

            if (!accept) {
                stringProps.remove(key);
            }
        }
    }
