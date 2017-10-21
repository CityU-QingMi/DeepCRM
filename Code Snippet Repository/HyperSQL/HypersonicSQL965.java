    Result getResult(Session session) {

        switch (type) {

            case StatementTypes.RETURN :
            case StatementTypes.CONDITION :
                Result result = expression.getResult(session);

                // data navigator has statement scope and will be cleared at the end of statement
                if (result.isData()) {
                    RowSetNavigatorData navigator =
                        new RowSetNavigatorData(session,
                                                result.getNavigator());

                    result.setNavigator(navigator);
                }

                return result;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "");
        }
    }
