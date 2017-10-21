    private long registerStatement(long csid, Statement cs) {

        cs.setCompileTimestamp(database.txManager.getGlobalChangeTimestamp());

        int              schemaid = cs.getSchemaName().hashCode();
        LongValueHashMap sqlMap   = (LongValueHashMap) schemaMap.get(schemaid);

        if (sqlMap == null) {
            sqlMap = new LongValueHashMap();

            schemaMap.put(schemaid, sqlMap);
        }

        if (csid < 0) {
            csid = nextID();
        }

        cs.setID(csid);
        sqlMap.put(cs.getSQL(), csid);
        csidMap.put(csid, cs);

        return csid;
    }
