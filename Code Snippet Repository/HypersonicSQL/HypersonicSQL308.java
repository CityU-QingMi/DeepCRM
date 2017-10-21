    private Statement compileSetTimeZone() {

        Expression e;

        readThis(Tokens.ZONE);

        if (token.tokenType == Tokens.LOCAL) {
            read();

            e = new ExpressionValue(null, Type.SQL_INTERVAL_HOUR_TO_MINUTE);
        } else {
            e = XreadIntervalValueExpression();

            HsqlList unresolved = e.resolveColumnReferences(session,
                RangeGroup.emptyGroup, RangeGroup.emptyArray, null);

            ExpressionColumn.checkColumnsResolved(unresolved);
            e.resolveTypes(session, null);

            if (e.dataType == null) {
                throw Error.error(ErrorCode.X_42563);
            }

            if (e.dataType.typeCode != Types.SQL_INTERVAL_HOUR_TO_MINUTE) {
                throw Error.error(ErrorCode.X_42563);
            }
        }

        return new StatementSession(session, compileContext,
                                    StatementTypes.SET_TIME_ZONE,
                                    new Expression[]{ e });
    }
