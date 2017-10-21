    Statement compileRenameObject(HsqlName name, int type) {

        HsqlName newName = readNewSchemaObjectName(type, true);
        String   sql     = getLastPart();

        switch (type) {

            case SchemaObject.CATALOG :
                break;

            case SchemaObject.SCHEMA :
                checkSchemaUpdateAuthorisation(session, name);
                break;

            default :
                name.setSchemaIfNull(session.getCurrentSchemaHsqlName());
                checkSchemaUpdateAuthorisation(session, name.schema);
        }

        Object[] args = new Object[] {
            name, newName
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogNameArray();

        return new StatementSchema(sql, StatementTypes.RENAME_OBJECT, args,
                                   null, writeLockNames);
    }
