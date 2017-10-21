    public PersistentStore newStore(Session session,
                                    PersistentStoreCollection collection,
                                    TableBase table) {

        switch (table.getTableType()) {

            case TableBase.CACHED_TABLE :
                DataFileCache cache = getCache();

                if (cache == null) {
                    break;
                }

                return new RowStoreAVLDisk(cache, (Table) table);

            case TableBase.MEMORY_TABLE :
            case TableBase.SYSTEM_TABLE :
                return new RowStoreAVLMemory((Table) table);

            case TableBase.TEXT_TABLE :
                return new RowStoreAVLDiskData((Table) table);

            case TableBase.INFO_SCHEMA_TABLE :
                return new RowStoreAVLHybridExtended(session, table, false);

            case TableBase.TEMP_TABLE :
                return new RowStoreAVLHybridExtended(session, table, true);

            case TableBase.CHANGE_SET_TABLE :
                return new RowStoreDataChange(session, table);

            case TableBase.FUNCTION_TABLE :
            case TableBase.RESULT_TABLE :
            case TableBase.SYSTEM_SUBQUERY :
            case TableBase.VIEW_TABLE :
            case TableBase.TRANSITION_TABLE :
                if (session == null) {
                    return null;
                }

                return new RowStoreAVLHybrid(session, table, true);
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "Logger");
    }
