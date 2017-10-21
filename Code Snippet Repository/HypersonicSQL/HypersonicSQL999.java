    void setOrCheckObjectName(Session session, HsqlName parent, HsqlName name,
                              boolean check) {

        if (name.schema == null) {
            name.schema = schemaName == null
                          ? session.getCurrentSchemaHsqlName()
                          : schemaName;
        } else {
            name.schema = session.getSchemaHsqlName(name.schema.name);

            if (name.schema == null) {
                throw Error.error(ErrorCode.X_42505);
            }

            if (isSchemaDefinition && schemaName != name.schema) {
                throw Error.error(ErrorCode.X_42505);
            }
        }

        name.parent = parent;

        if (!isSchemaDefinition) {
            checkSchemaUpdateAuthorisation(session, name.schema);
        }

        if (check) {
            session.database.schemaManager.checkSchemaObjectNotExists(name);
        }
    }
