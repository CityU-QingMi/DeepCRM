    private static void dropUser(Session session, HsqlName name,
                                 boolean cascade) {

        Grantee grantee = session.database.getUserManager().get(name.name);

        if (session.database.getSessionManager().isUserActive(name.name)) {
            throw Error.error(ErrorCode.X_42539);
        }

        if (!cascade && session.database.schemaManager.hasSchemas(grantee)) {
            HsqlArrayList list =
                session.database.schemaManager.getSchemas(grantee);
            Schema schema = (Schema) list.get(0);

            throw Error.error(ErrorCode.X_42502,
                              schema.getName().statementName);
        }

        session.database.schemaManager.dropSchemas(session, grantee, cascade);
        session.database.getUserManager().dropUser(name.name);
    }
