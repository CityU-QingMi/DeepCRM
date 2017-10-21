    private void dropType(Session session, HsqlName name, boolean cascade) {

        checkSchemaUpdateAuthorisation(session, name.schema);

        Type distinct =
            (Type) session.database.schemaManager.getSchemaObject(name);

        session.database.schemaManager.removeSchemaObject(name, cascade);

        distinct.userTypeModifier = null;
    }
