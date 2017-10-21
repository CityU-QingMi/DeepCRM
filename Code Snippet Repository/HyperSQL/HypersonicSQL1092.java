    synchronized IndexUse[] getIndexForColumns(Session session,
            OrderedIntHashSet set, int opType, boolean ordered) {

        if (set.isEmpty()) {
            return Index.emptyUseArray;
        }

        IndexUse[] indexUse = findIndexForColumns(session, set, opType,
            ordered);

        if (indexUse.length == 0) {
            Index selected = null;

            // index is not full;
            switch (tableType) {

                case TableBase.TEMP_TABLE :
                case TableBase.INFO_SCHEMA_TABLE :
                case TableBase.MODULE_TABLE :
                case TableBase.FUNCTION_TABLE :
                case TableBase.SYSTEM_SUBQUERY :
                case TableBase.VIEW_TABLE : {
                    selected = createIndexForColumns(session, set.toArray());

                    break;
                }
            }

            if (selected != null) {
                indexUse = selected.asArray();
            }
        }

        return indexUse;
    }
