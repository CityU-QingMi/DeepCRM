    void addTrigger(TriggerDef td, HsqlName otherName) {

        switch (td.operationType) {

            case StatementTypes.INSERT :
                if (isTriggerInsertable) {
                    throw Error.error(ErrorCode.X_42538);
                }

                isTriggerInsertable = true;
                break;

            case StatementTypes.DELETE_WHERE :
                if (isTriggerDeletable) {
                    throw Error.error(ErrorCode.X_42538);
                }

                isTriggerDeletable = true;
                break;

            case StatementTypes.UPDATE_WHERE :
                if (isTriggerUpdatable) {
                    throw Error.error(ErrorCode.X_42538);
                }

                isTriggerUpdatable = true;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "View");
        }

        super.addTrigger(td, otherName);
    }
