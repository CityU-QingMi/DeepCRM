    public String getPropertyString(String key) {

        Object[] metaData = (Object[]) dbMeta.get(key);

        if (metaData == null) {
            throw Error.error(ErrorCode.X_42555, key);
        }

        String prop = stringProps.getProperty(key);
        boolean isSystem =
            ((Integer) metaData[HsqlProperties.indexType]).intValue()
            == SYSTEM_PROPERTY;

        if (prop == null && isSystem) {
            try {
                prop = System.getProperty(key);
            } catch (SecurityException e) {}
        }

        if (prop == null) {
            Object value = metaData[HsqlProperties.indexDefaultValue];

            if (value == null) {
                return null;
            }

            return String.valueOf(value);
        }

        return prop;
    }
