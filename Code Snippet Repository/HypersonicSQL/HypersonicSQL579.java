    public RangeVariable(HashMappedList variables, SimpleName rangeName,
                         boolean isVariable, int rangeType) {

        this.variables   = variables;
        this.rangeType   = rangeType;
        rangeTable       = null;
        tableAlias       = rangeName;
        columnsInGroupBy = null;
        usedColumns      = null;
        joinConditions = new RangeVariableConditions[]{
            new RangeVariableConditions(this, true) };
        whereConditions = new RangeVariableConditions[]{
            new RangeVariableConditions(this, false) };

        switch (rangeType) {

            case TRANSITION_RANGE :
                usedColumns = new boolean[variables.size()];
            case PARAMETER_RANGE :
            case VARIALBE_RANGE :
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "RangeVariable");
        }
    }
