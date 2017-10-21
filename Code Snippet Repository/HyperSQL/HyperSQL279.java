    public void init() {
        if (applicationKey == null) {
            throw new IllegalStateException(
                    "Required property 'applicationKey' not set");
        }
        if (roleSchemaViaCredential && roleSchemaValuePattern == null) {
            throw new IllegalStateException(
                    "Properties 'roleSchemaViaCredential' and "
                    + "'roleSchemaValuePattern' are mutually exclusive.  "
                    + "If you want JaasAuthBean to manage roles or schemas, "
                    + "you must set property 'roleSchemaValuePattern'.");
        }
        initialized = true;
    }
