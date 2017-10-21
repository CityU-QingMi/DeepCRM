    public ResultMetaData getResultMetaData() {

        switch (type) {

            case StatementTypes.DELETE_WHERE :
            case StatementTypes.INSERT :
            case StatementTypes.UPDATE_WHERE :
            case StatementTypes.MERGE :
                return ResultMetaData.emptyResultMetaData;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementDMQL");
        }
    }
