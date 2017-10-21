    Statement compileReturnValue(Routine routine, StatementCompound context) {

        RangeGroup[] rangeGroups = new RangeGroup[1];

        rangeGroups[0] = context == null ? routine
                                         : context;

        compileContext.setOuterRanges(rangeGroups);

        Expression e = XreadValueExpressionOrNull();

        if (e == null) {
            throw unexpectedToken();
        }

        resolveOuterReferencesAndTypes(routine, context, e);

        if (routine.isProcedure()) {
            throw Error.parseError(ErrorCode.X_42602, null,
                                   scanner.getLineNumber());
        }

        if (routine.returnsTable()) {
            if (e.getType() != OpTypes.TABLE_SUBQUERY) {
                throw Error.parseError(ErrorCode.X_42611, null,
                                       scanner.getLineNumber());
            }
        }

        Type returnType   = new RowType(e.getNodeDataTypes());
        Type declaredType = routine.getReturnType();

        if (!declaredType.isRowType()) {
            declaredType = new RowType(new Type[]{ routine.getReturnType() });
        }

        if (declaredType.getDegree() != returnType.getDegree()) {
            throw Error.parseError(ErrorCode.X_42564, null,
                                   scanner.getLineNumber());
        }

        if (!declaredType.canBeAssignedFrom(returnType)) {
            throw Error.parseError(ErrorCode.X_42611, null,
                                   scanner.getLineNumber());
        }

        return new StatementExpression(session, compileContext,
                                       StatementTypes.RETURN, e);
    }
