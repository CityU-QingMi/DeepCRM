    public String getSQL() {

        switch (opType) {

            case OpTypes.VALUE :
                if (valueData == null) {
                    return Tokens.T_NULL;
                }

                return dataType.convertToSQLString(valueData);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "ExpressionValue");
        }
    }
