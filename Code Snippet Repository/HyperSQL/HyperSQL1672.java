    public String getStringPropertyDefault(String key) {

        Object[] metaData = (Object[]) dbMeta.get(key);

        if (metaData == null) {
            throw Error.error(ErrorCode.X_42555, key);
        }

        return (String) metaData[HsqlProperties.indexDefaultValue];
    }
