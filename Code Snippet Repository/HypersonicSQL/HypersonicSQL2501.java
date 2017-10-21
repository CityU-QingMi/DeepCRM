    public boolean isPropertyTrue(String key) {

        Boolean  value;
        Object[] metaData = (Object[]) dbMeta.get(key);

        if (metaData == null) {
            throw Error.error(ErrorCode.X_42555, key);
        }

        value = (Boolean) metaData[HsqlProperties.indexDefaultValue];

        String prop = null;
        boolean isSystem =
            ((Integer) metaData[HsqlProperties.indexType]).intValue()
            == SYSTEM_PROPERTY;

        if (isSystem) {
            try {
                prop = System.getProperty(key);
            } catch (SecurityException e) {}
        } else {
            prop = stringProps.getProperty(key);
        }

        if (prop != null) {
            value = Boolean.valueOf(prop);
        }

        return value.booleanValue();
    }
