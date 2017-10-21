    synchronized void freeStatement(long csid) {

        if (csid == -1) {

            // statement was never added
            return;
        }

        int useCount = useMap.get(csid, 1);

        if (useCount > 1) {
            useMap.put(csid, useCount - 1);

            return;
        }

        Statement cs = (Statement) csidMap.remove(csid);

        if (cs != null) {
            int schemaid = cs.getSchemaName().hashCode();
            LongValueHashMap sqlMap =
                (LongValueHashMap) schemaMap.get(schemaid);
            String sql = cs.getSQL();

            sqlMap.remove(sql);
        }

        useMap.remove(csid);
    }
