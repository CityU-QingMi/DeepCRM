    Result getResult(Session session) {

        switch (type) {

            case StatementTypes.ITERATE :
            case StatementTypes.LEAVE :
                return Result.newPSMResult(type, label.name, null);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementSimple");
        }
    }
