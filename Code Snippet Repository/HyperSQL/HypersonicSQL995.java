    private static void dropDomain(Session session, HsqlName name,
                                   boolean cascade) {

        Type domain =
            (Type) session.database.schemaManager.getSchemaObject(name);
        OrderedHashSet set =
            session.database.schemaManager.getReferencesTo(domain.getName());

        if (!cascade && set.size() > 0) {
            HsqlName objectName = (HsqlName) set.get(0);

            throw Error.error(ErrorCode.X_42502,
                              objectName.getSchemaQualifiedStatementName());
        }

        Constraint[] constraints = domain.userTypeModifier.getConstraints();

        set = new OrderedHashSet();

        for (int i = 0; i < constraints.length; i++) {
            set.add(constraints[i].getName());
        }

        session.database.schemaManager.removeSchemaObjects(set);
        session.database.schemaManager.removeSchemaObject(domain.getName(),
                cascade);

        domain.userTypeModifier = null;
    }
