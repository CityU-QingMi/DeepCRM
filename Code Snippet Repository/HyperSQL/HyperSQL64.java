    synchronized Index getIndexForColumn(Session session, int col) {

        int i = bestIndexForColumn[col];

        if (i > -1) {
            return indexList[i];
        }

        switch (tableType) {

            case TableBase.TEMP_TABLE :
            case TableBase.INFO_SCHEMA_TABLE :
            case TableBase.MODULE_TABLE :
            case TableBase.FUNCTION_TABLE :
            case TableBase.SYSTEM_SUBQUERY :
            case TableBase.VIEW_TABLE : {
                Index index = createIndexForColumns(session, new int[]{ col });

                return index;
            }
        }

        return null;
    }
