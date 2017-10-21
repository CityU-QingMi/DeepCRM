    public void compile(Session session, SchemaObject table) {

        if (generatingExpression == null) {
            return;
        }

        generatingExpression.resetColumnReferences();
        generatingExpression.resolveCheckOrGenExpression(
            session,
            new RangeGroupSimple(((Table) table).getDefaultRanges(), false),
            false);

        if (!dataType.canBeAssignedFrom(generatingExpression.getDataType())) {
            throw Error.error(ErrorCode.X_42561);
        }

        setReferences();
    }
