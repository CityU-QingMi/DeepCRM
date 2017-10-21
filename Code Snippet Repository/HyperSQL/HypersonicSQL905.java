    public Session getSysSessionForScript(Database db) {

        Session session = new Session(db, db.getUserManager().getSysUser(),
                                      false, false, 0, null, 0);

        // some old 1.8.0 do not have SET SCHEMA PUBLIC
        session.setCurrentSchemaHsqlName(
            db.schemaManager.defaultSchemaHsqlName);

        session.isProcessingScript = true;

        return session;
    }
