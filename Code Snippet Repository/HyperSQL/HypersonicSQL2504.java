    public int getIntegerProperty(String key) {

        int      value;
        Object[] metaData = (Object[]) dbMeta.get(key);

        if (metaData == null) {
            throw Error.error(ErrorCode.X_42555, key);
        }

        value =
            ((Integer) metaData[HsqlProperties.indexDefaultValue]).intValue();

        String prop = stringProps.getProperty(key);

        if (prop != null) {
            try {
                value = Integer.parseInt(prop);
            } catch (NumberFormatException e) {}
        }

        return value;
    }
