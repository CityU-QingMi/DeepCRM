    public String getTableTypeString() {

        switch (tableType) {

            case TableBase.MEMORY_TABLE :
                return Tokens.T_MEMORY;

            case TableBase.CACHED_TABLE :
                return Tokens.T_CACHED;

            case TableBase.TEXT_TABLE :
                return Tokens.T_TEXT;

            case TableBase.MODULE_TABLE :
                return Tokens.T_MODULE;

            case TableBase.FUNCTION_TABLE :
                return Tokens.T_FUNCTION;

            case TableBase.INFO_SCHEMA_TABLE :
            case TableBase.VIEW_TABLE :
                return Tokens.T_VIEW;

            case TableBase.TEMP_TABLE :
                return Tokens.T_TEMP;

            case TableBase.SYSTEM_SUBQUERY :
            default :
                return "SUBQUERY";
        }
    }
