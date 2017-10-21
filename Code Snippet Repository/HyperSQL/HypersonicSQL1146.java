    public boolean setTableType(Session session, int newType) {

        int currentType = table.getTableType();

        if (currentType == newType) {
            return false;
        }

        switch (newType) {

            case TableBase.CACHED_TABLE :
                break;

            case TableBase.MEMORY_TABLE :
                break;

            default :
                return false;
        }

        Table tn;

        try {
            tn = table.moveDefinition(session, newType, null, null, null, -1,
                                      0, emptySet, emptySet);

            moveData(table, tn, -1, 0);
        } catch (HsqlException e) {
            return false;
        }

        setNewTableInSchema(tn);
        updateConstraints(tn, emptySet);

        table = tn;

        database.schemaManager.recompileDependentObjects(table);

        return true;
    }
