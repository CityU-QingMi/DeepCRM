    public TableDerived(Database database, HsqlName name, int type) {

        super(database, name, type);

        switch (type) {

            // for special use, not INFORMATION_SCHEMA views
            case TableBase.CHANGE_SET_TABLE :
            case TableBase.SYSTEM_TABLE :
            case TableBase.FUNCTION_TABLE :
            case TableBase.VIEW_TABLE :
            case TableBase.RESULT_TABLE :
            case TableBase.SYSTEM_SUBQUERY :
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Table");
        }
    }
