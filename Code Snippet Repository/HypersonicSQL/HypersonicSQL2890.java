    public boolean isFullyAccessibleByRole(HsqlName name) {

        Grantee owner;

        if (isAdmin) {
            return true;
        }

        if (name.type == SchemaObject.SCHEMA) {
            owner = name.owner;
        } else if (name.schema == null) {
            return false;
        } else {
            owner = name.schema.owner;
        }

        if (owner == this) {
            return true;
        }

        if (hasRole(owner)) {
            return true;
        }

        return false;
    }
