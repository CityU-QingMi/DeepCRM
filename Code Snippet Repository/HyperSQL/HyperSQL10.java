    public void resolve(Session session) {

        switch (operationType) {

            case StatementSet.TRIGGER_SET :
                for (int i = 0; i < updateExpressions.length; i++) {
                    updateExpressions[i].collectObjectNames(references);
                }
                break;

            case StatementSet.SELECT_INTO :
            case StatementSet.VARIABLE_SET : {
                if (expression != null) {
                    expression.collectObjectNames(references);
                }

                break;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementSet");
        }
    }
