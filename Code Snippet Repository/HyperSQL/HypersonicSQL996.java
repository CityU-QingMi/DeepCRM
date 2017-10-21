    private static void dropRole(Session session, HsqlName name,
                                 boolean cascade) {

        Grantee role = session.database.getGranteeManager().getRole(name.name);

        if (!cascade && session.database.schemaManager.hasSchemas(role)) {
            HsqlArrayList list =
                session.database.schemaManager.getSchemas(role);
            Schema schema = (Schema) list.get(0);

            throw Error.error(ErrorCode.X_42502,
                              schema.getName().statementName);
        }

        session.database.schemaManager.dropSchemas(session, role, cascade);
        session.database.getGranteeManager().dropRole(name.name);
    }
