    static int getOperationType(int token) {

        switch (token) {

            case Tokens.INSERT :
                return StatementTypes.INSERT;

            case Tokens.DELETE :
                return StatementTypes.DELETE_WHERE;

            case Tokens.UPDATE :
                return StatementTypes.UPDATE_WHERE;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "TriggerDef");
        }
    }
