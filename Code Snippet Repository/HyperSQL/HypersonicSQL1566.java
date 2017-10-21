    private void setLocalVariables() {
        if (connProperties == null) {
            return;
        }

        isCloseResultSet = connProperties.isPropertyTrue(
            HsqlDatabaseProperties.url_close_result, false);
        isUseColumnName = connProperties.isPropertyTrue(
            HsqlDatabaseProperties.url_get_column_name, true);
        isEmptyBatchAllowed = connProperties.isPropertyTrue(
            HsqlDatabaseProperties.url_allow_empty_batch, false);
        isTranslateTTIType = clientProperties.isPropertyTrue(
            HsqlDatabaseProperties.jdbc_translate_tti_types, true);
        isStoreLiveObject = clientProperties.isPropertyTrue(
            HsqlDatabaseProperties.sql_live_object, false);

        if (isStoreLiveObject)  {
            String connType = connProperties.getProperty("connection_type");
            if(!DatabaseURL.S_MEM.equals(connType))
            isStoreLiveObject = false;
        }

    }
