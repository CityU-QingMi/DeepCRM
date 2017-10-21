    public void createSchema() {

        sysLobSession = database.sessionManager.getSysLobSession();

        InputStream fis = (InputStream) AccessController.doPrivileged(
            new PrivilegedAction() {

            public InputStream run() {
                return getClass().getResourceAsStream(resourceFileName);
            }
        });
        InputStreamReader reader = null;

        try {
            reader = new InputStreamReader(fis, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            reader = new InputStreamReader(fis);
        }

        LineNumberReader lineReader = new LineNumberReader(reader);
        LineGroupReader  lg = new LineGroupReader(lineReader, starters);
        HashMappedList   map        = lg.getAsMap();

        lg.close();

        String    sql       = (String) map.get("/*lob_schema_definition*/");
        Statement statement = sysLobSession.compileStatement(sql);
        Result    result    = statement.execute(sysLobSession);

        if (result.isError()) {
            throw result.getException();
        }

        HsqlName name =
            database.schemaManager.getSchemaHsqlName("SYSTEM_LOBS");
        Table table = database.schemaManager.getUserTable("BLOCKS",
            "SYSTEM_LOBS");

        compileStatements();
    }
