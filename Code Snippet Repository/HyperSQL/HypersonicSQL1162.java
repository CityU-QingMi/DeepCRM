    void setDataSource(Session session, String dataSourceNew,
                       boolean isReversedNew, boolean createFile) {

        if (getTableType() == Table.TEMP_TEXT_TABLE) {

            //
        } else {
            session.getGrantee().checkSchemaUpdateOrGrantRights(
                getSchemaName().name);
        }

        dataSourceNew = dataSourceNew.trim();

        //-- Open if descending, direction changed, file changed, or not connected currently
        if (isReversedNew || (isReversedNew != isReversed)
                || !dataSource.equals(dataSourceNew) || !isConnected) {
            openCache(session, dataSourceNew, isReversedNew, isReadOnly);
        }

        if (isReversed) {
            isReadOnly = true;
        }
    }
