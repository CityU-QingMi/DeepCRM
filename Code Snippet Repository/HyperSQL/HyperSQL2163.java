    public HsqlName getInitialOrDefaultSchema() {

        if (initialSchema != null) {
            return initialSchema;
        }

        HsqlName schema =
            granteeManager.database.schemaManager.findSchemaHsqlName(
                getName().getNameString());

        if (schema == null) {
            return granteeManager.database.schemaManager
                .getDefaultSchemaHsqlName();
        } else {
            return schema;
        }
    }
