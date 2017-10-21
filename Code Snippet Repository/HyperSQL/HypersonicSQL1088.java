    int indexTypeForColumn(Session session, int col) {

        int i = bestIndexForColumn[col];

        if (i > -1) {
            return indexList[i].isUnique()
                   && indexList[i].getColumnCount() == 1 ? Index.INDEX_UNIQUE
                                                         : Index
                                                         .INDEX_NON_UNIQUE;
        }

        switch (tableType) {

            case TableBase.TEMP_TABLE :
            case TableBase.INFO_SCHEMA_TABLE :
            case TableBase.MODULE_TABLE :
            case TableBase.FUNCTION_TABLE :
            case TableBase.SYSTEM_SUBQUERY :
            case TableBase.VIEW_TABLE : {
                return Index.INDEX_NON_UNIQUE;
            }
        }

        return Index.INDEX_NONE;
    }
