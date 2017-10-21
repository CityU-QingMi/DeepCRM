    public void init() {
        if (ldapHost == null) {
            throw new IllegalStateException(
                    "Required property 'ldapHost' not set");
        }
        if (parentDn == null) {
            throw new IllegalStateException(
                    "Required property 'parentDn' not set");
        }
        if (initialContextFactory == null) {
            throw new IllegalStateException(
                    "Required property 'initialContextFactory' not set");
        }
        if (mechanism == null) {
            throw new IllegalStateException(
                    "Required property 'mechanism' not set");
        }
        if (rdnAttribute == null) {
            throw new IllegalStateException(
                    "Required property 'rdnAttribute' not set");
        }
        if (rolesSchemaAttribute == null && accessAttribute == null) {
            throw new IllegalStateException(
                    "You must set property 'rolesSchemaAttribute' "
                    + "and/or property 'accessAttribute'");
        }
        if (roleSchemaValuePattern != null && rolesSchemaAttribute == null) {
            throw new IllegalStateException(
                    "If property 'roleSchemaValuePattern' is set, then you "
                    + "must also set property 'rolesSchemaAttribute' to "
                    + "indicate which attribute to evaluate");
        }
        if (accessValuePattern != null && accessAttribute == null) {
            throw new IllegalStateException(
                    "If property 'accessValuePattern' is set, then you "
                    + "must also set property 'accessAttribute' to "
                    + "indicate which attribute to evaluate");
        }
        if (rolesSchemaAttribute != null && accessAttribute != null) {
            attributeUnion = new String[]
                    { rolesSchemaAttribute, accessAttribute };
        } else if (rolesSchemaAttribute != null) {
            attributeUnion = new String[] { rolesSchemaAttribute };
        } else {
            attributeUnion = new String[] { accessAttribute };
        }
        initialized = true;
    }
