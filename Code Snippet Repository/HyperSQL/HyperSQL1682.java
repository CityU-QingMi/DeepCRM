    public boolean setDatabaseProperty(String key, String value) {

        Object[] meta  = (Object[]) dbMeta.get(key);
        String   error = HsqlProperties.validateProperty(key, value, meta);

        if (error != null) {
            return false;
        }

        stringProps.put(key, value);

        return true;
    }
