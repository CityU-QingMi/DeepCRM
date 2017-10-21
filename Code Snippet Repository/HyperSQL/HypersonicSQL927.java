    public ResultMetaData getResultMetaData() {

        switch (type) {

            case StatementTypes.EXPLAIN_PLAN :
                return ResultMetaData.newSingleColumnMetaData("OPERATION");

            case StatementTypes.DATABASE_SCRIPT :
                if (statementReturnType == StatementTypes.RETURN_RESULT) {
                    return ResultMetaData.newSingleColumnMetaData(
                        "STATEMENTS");
                }

            // fall through
            default :
                return super.getResultMetaData();
        }
    }
