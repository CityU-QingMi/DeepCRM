    public Object[] getRowValue(Session session) {

        switch (opType) {

            case OpTypes.TABLE : {
                return table.queryExpression.getValues(session);
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Expression");
        }
    }
