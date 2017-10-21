    public String getEventTypeString() {

        switch (this.operationType) {

            case StatementTypes.INSERT :
                return Tokens.T_INSERT;

            case StatementTypes.DELETE_WHERE :
                return Tokens.T_DELETE;

            case StatementTypes.UPDATE_WHERE :
                return Tokens.T_UPDATE;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "TriggerDef");
        }
    }
