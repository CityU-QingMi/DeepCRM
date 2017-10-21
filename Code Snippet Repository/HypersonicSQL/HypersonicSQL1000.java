    void setSchemaName(Session session, HsqlName parent, HsqlName name) {

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
    }
