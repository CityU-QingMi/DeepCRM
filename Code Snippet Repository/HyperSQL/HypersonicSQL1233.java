    static int getTiming(int token) {

        switch (token) {

            case Tokens.BEFORE :
                return TriggerDef.BEFORE;

            case Tokens.AFTER :
                return TriggerDef.AFTER;

            case Tokens.INSTEAD :
                return TriggerDef.INSTEAD;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "TriggerDef");
        }
    }
