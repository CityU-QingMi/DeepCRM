    public final boolean isSchemaBaseTable() {

        switch (tableType) {

            case TableBase.MEMORY_TABLE :
            case TableBase.CACHED_TABLE :
            case TableBase.TEXT_TABLE :
                return true;

            default :
                return false;
        }
    }
