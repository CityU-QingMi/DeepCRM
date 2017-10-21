    synchronized Statement compile(Session session,
                                   Result cmd) throws Throwable {

        int       props = cmd.getExecuteProperties();
        Statement cs    = null;
        String    sql   = cmd.getMainString();
        long      csid  = getStatementID(session.currentSchema, sql);

        if (csid >= 0) {
            cs = (Statement) csidMap.get(csid);
        }

        // generated result props still overwrite earlier version
        if (cs == null || !cs.isValid() || cs.getCompileTimestamp() < database
                .schemaManager.getSchemaChangeTimestamp() || cs
                .getCursorPropertiesRequest() != props) {
            cs = session.compileStatement(sql, props);

            cs.setCursorPropertiesRequest(props);

            csid = registerStatement(csid, cs);
        }

        int useCount = useMap.get(csid, 0) + 1;

        useMap.put(csid, useCount);
        cs.setGeneratedColumnInfo(cmd.getGeneratedResultType(),
                                  cmd.getGeneratedResultMetaData());

        return cs;
    }
