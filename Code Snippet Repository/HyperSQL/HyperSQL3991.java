    public synchronized Statement getStatement(Session session,
            Statement statement) {

        long      csid = statement.getID();
        Statement cs   = (Statement) csidMap.get(csid);

        if (cs != null) {
            return getStatement(session, csid);
        }

        cs = recompileStatement(session, statement);

        return cs;
    }
