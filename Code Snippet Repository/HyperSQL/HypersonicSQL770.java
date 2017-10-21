    public Collation getCollation(Session session, String name,
                                  String schemaName) {

        Collation collation = null;

        if (schemaName == null
                || SqlInvariants.INFORMATION_SCHEMA.equals(schemaName)) {
            try {
                collation = Collation.getCollation(name);
            } catch (HsqlException e) {}
        }

        if (collation == null) {
            schemaName = session.getSchemaName(schemaName);
            collation = (Collation) getSchemaObject(name, schemaName,
                    SchemaObject.COLLATION);
        }

        return collation;
    }
