    Result getResult(Session session) {

        switch (type) {

            /** @todo - check sqlState against allowed values */
            case StatementTypes.SIGNAL :
            case StatementTypes.RESIGNAL :
                HsqlException ex = Error.error(getMessage(session), sqlState);

                return Result.newErrorResult(ex);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementSignal");
        }
    }
