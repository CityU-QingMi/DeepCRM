    void setUpIndexesAndTypes() {

        triggerType = 0;

        switch (operationType) {

            case StatementTypes.INSERT :
                triggerType = Trigger.INSERT_AFTER;
                break;

            case StatementTypes.DELETE_WHERE :
                triggerType = Trigger.DELETE_AFTER;
                break;

            case StatementTypes.UPDATE_WHERE :
                triggerType = Trigger.UPDATE_AFTER;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "TriggerDef");
        }

        if (forEachRow) {
            triggerType += NUM_TRIGGER_OPS;
        }

        if (actionTiming == TriggerDef.BEFORE
                || actionTiming == TriggerDef.INSTEAD) {
            triggerType += NUM_TRIGGER_OPS;
        }
    }
