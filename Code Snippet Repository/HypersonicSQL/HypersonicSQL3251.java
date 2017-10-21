    public void testTwo() throws Exception {

        Connection conn = newConnection();
        int        updateCount;

        try {
            TestUtil.testScript(conn, "testrun/hsqldb/TestSelf.txt");

            DatabaseMetaData dbmeta = conn.getMetaData();

            dbmeta.allProceduresAreCallable();
            dbmeta.getBestRowIdentifier(null, null, "T_1",
                                        DatabaseMetaData.bestRowTransaction,
                                        true);
            dbmeta.getCatalogs();
            dbmeta.getColumnPrivileges(null, "PUBLIC", "T_1", "%");
            dbmeta.getColumns("PUBLIC", "PUBLIC", "T_1", "%");
            dbmeta.getCrossReference(null, null, "T_1", null, null, "T_1");
            dbmeta.getExportedKeys(null, null, "T_1");
            dbmeta.getFunctionColumns(null, "%", "%", "%");
            dbmeta.getFunctions(null, "%", "%");
            dbmeta.getImportedKeys("PUBLIC", "PUBLIC", "T_1");
            dbmeta.getIndexInfo("PUBLIC", "PUBLIC", "T1", true, true);
            dbmeta.getPrimaryKeys("PUBLIC", "PUBLIC", "T_1");
            dbmeta.getProcedureColumns(null, null, "%", "%");
            dbmeta.getProcedures("PUBLIC", "%", "%");
            dbmeta.getSchemas(null, "#");
            dbmeta.getTablePrivileges(null, "%", "%");
            dbmeta.getUDTs(null, "%", "%", new int[]{ Types.DISTINCT });
        } catch (Exception e) {
            assertTrue("unable to prepare or execute DDL", false);
        } finally {
            conn.close();
        }
    }
