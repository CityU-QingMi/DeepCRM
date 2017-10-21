    public Result getResult(Session session) {

        switch (opType) {

            case OpTypes.TABLE : {
                RowSetNavigatorData navigator = table.getNavigator(session);
                Result              result    = Result.newResult(navigator);

                result.metaData = table.queryExpression.getMetaData();

                return result;
            }
            default : {
                throw Error.runtimeError(ErrorCode.U_S0500, "ExpressionTable");
            }
        }
    }
