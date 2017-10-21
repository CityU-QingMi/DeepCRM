    void removeTrigger(TriggerDef td) {

        switch (td.operationType) {

            case StatementTypes.INSERT :
                isTriggerInsertable = false;
                break;

            case StatementTypes.DELETE_WHERE :
                isTriggerDeletable = false;
                break;

            case StatementTypes.UPDATE_WHERE :
                isTriggerUpdatable = false;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "View");
        }

        super.removeTrigger(td);
    }
