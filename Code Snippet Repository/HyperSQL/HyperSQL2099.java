    public boolean hasSchemaUpdateOrGrantRights(String schemaName) {

        // If a DBA
        if (isAdmin()) {
            return true;
        }

        Grantee schemaOwner =
            granteeManager.database.schemaManager.toSchemaOwner(schemaName);

        // If owner of Schema
        if (schemaOwner == this) {
            return true;
        }

        // If a member of Schema authorization role
        if (hasRole(schemaOwner)) {
            return true;
        }

        return false;
    }
