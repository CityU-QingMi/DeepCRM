    String getHsqlType() {

        switch (table.getTableType()) {

            case TableBase.MEMORY_TABLE :
            case TableBase.TEMP_TABLE :
            case TableBase.INFO_SCHEMA_TABLE :
                return "MEMORY";

            case TableBase.CACHED_TABLE :
                return "CACHED";

            case TableBase.TEMP_TEXT_TABLE :
            case TableBase.TEXT_TABLE :
                return "TEXT";

            case TableBase.VIEW_TABLE :
            default :
                return null;
        }
    }
