    public synchronized Statement getStatement(Session session, long csid) {

        Statement cs = (Statement) csidMap.get(csid);

        if (cs == null) {
            return null;
        }

        if (cs.getCompileTimestamp()
                < database.schemaManager.getSchemaChangeTimestamp()) {
            Statement newStatement = recompileStatement(session, cs);

            if (newStatement == null) {
                freeStatement(csid);

                return null;
            }

            registerStatement(cs.getID(), newStatement);

            return newStatement;
        }

        return cs;
    }
