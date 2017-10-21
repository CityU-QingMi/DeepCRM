   public static JDBCSessionDataStore.SessionTableSchema newSessionTableSchema()
   {
       JDBCSessionDataStore.SessionTableSchema sessionTableSchema = new JDBCSessionDataStore.SessionTableSchema();
       sessionTableSchema.setTableName(TABLE);
       sessionTableSchema.setIdColumn(ID_COL);
       sessionTableSchema.setAccessTimeColumn(ACCESS_COL);
       sessionTableSchema.setContextPathColumn(CONTEXT_COL);
       sessionTableSchema.setCookieTimeColumn(COOKIE_COL);
       sessionTableSchema.setCreateTimeColumn(CREATE_COL);
       sessionTableSchema.setExpiryTimeColumn(EXPIRY_COL);
       sessionTableSchema.setLastAccessTimeColumn(LAST_ACCESS_COL);
       sessionTableSchema.setLastNodeColumn(LAST_NODE_COL);
       sessionTableSchema.setLastSavedTimeColumn(LAST_SAVE_COL);
       sessionTableSchema.setMapColumn(MAP_COL);
       sessionTableSchema.setMaxIntervalColumn(MAX_IDLE_COL);
       return sessionTableSchema;
   }
