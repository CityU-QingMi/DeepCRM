    public String getStringProperty(String key) {

        String   value;
        Object[] metaData = (Object[]) dbMeta.get(key);

        if (metaData == null) {
            throw Error.error(ErrorCode.X_42555, key);
        }

        value = (String) metaData[HsqlProperties.indexDefaultValue];

        String prop = stringProps.getProperty(key);

        if (prop != null) {
            value = prop;
        }

        return value;
    }
