    public Result execute(Session session) {

        Result result;

        switch (type) {

            case StatementTypes.BEGIN_END : {
                initialiseVariables(session);

                result = executeBlock(session);

                break;
            }
            case StatementTypes.FOR :
                result = executeForLoop(session);
                break;

            case StatementTypes.LOOP :
            case StatementTypes.WHILE :
            case StatementTypes.REPEAT : {
                result = executeLoop(session);

                break;
            }
            case StatementTypes.IF : {
                result = executeIf(session);

                break;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "StatementCompound");
        }

        if (result.isError()) {
            result.getException().setStatementType(group, type);
        }

        return result;
    }
