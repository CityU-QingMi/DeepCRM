    String getJDBCStandardType() {

        switch (table.getTableType()) {

            case TableBase.VIEW_TABLE :
                return "VIEW";

            case TableBase.TEMP_TABLE :
            case TableBase.TEMP_TEXT_TABLE :
                return "GLOBAL TEMPORARY";

            case TableBase.INFO_SCHEMA_TABLE :
                return "SYSTEM TABLE";

            default :
                if (table.getOwner().isSystem()) {
                    return "SYSTEM TABLE";
                }

                return "TABLE";
        }
    }
