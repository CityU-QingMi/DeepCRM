    StatementDMQL compileShortCursorSpecification(int props) {

        QueryExpression select = XreadSimpleTable();

        if (ResultProperties.isUpdatable(props)) {
            select.isUpdatable = true;
        }

        select.setReturningResult();
        select.resolve(session);

        StatementDMQL cs = new StatementQuery(session, select, compileContext);

        return cs;
    }
