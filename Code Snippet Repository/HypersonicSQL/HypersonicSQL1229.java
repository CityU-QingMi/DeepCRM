    public String getActionTimingString() {

        switch (this.actionTiming) {

            case TriggerDef.BEFORE :
                return Tokens.T_BEFORE;

            case TriggerDef.AFTER :
                return Tokens.T_AFTER;

            case TriggerDef.INSTEAD :
                return Tokens.T_INSTEAD + ' ' + Tokens.T_OF;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "TriggerDef");
        }
    }
