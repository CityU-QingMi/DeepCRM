    public Session getSysSession() {

        sysSession.currentSchema =
            sysSession.database.schemaManager.getDefaultSchemaHsqlName();
        sysSession.isProcessingScript = false;
        sysSession.isProcessingLog    = false;

        sysSession.setUser(sysSession.database.getUserManager().getSysUser());

        return sysSession;
    }
