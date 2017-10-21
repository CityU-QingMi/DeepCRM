    public Table findTable(Session session, String name, String prefix,
                           String prePrefix) {

        Table t;

        if (prefix == null) {
            t = findSessionTable(session, name);

            if (t != null) {
                return t;
            }
        }

        if (prePrefix == null) {
            if (Tokens.T_SESSION.equals(prefix)) {
                t = findSessionTable(session, name);

                if (t != null) {
                    return t;
                }
            } else if (SqlInvariants.INFORMATION_SCHEMA.equals(prefix)
                       && database.dbInfo != null) {
                t = database.dbInfo.getSystemTable(session, name);

                if (t != null) {
                    return t;
                }
            }
        }

        t = (Table) findSchemaObject(session, name, prefix, prePrefix,
                                     SchemaObject.TABLE);

        return t;
    }
