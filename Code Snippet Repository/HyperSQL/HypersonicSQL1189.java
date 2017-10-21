    Statement updateCurrentStatement(Session session, Statement cs) {

        if (cs.getCompileTimestamp()
                < database.schemaManager.getSchemaChangeTimestamp()) {
            cs = session.statementManager.getStatement(session, cs);
            session.sessionContext.currentStatement = cs;
        }

        return cs;
    }
