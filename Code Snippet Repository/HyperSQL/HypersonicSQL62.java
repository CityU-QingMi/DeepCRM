    private Expression getNewCheckExpression(Session session) {

        String    ddl     = check.getSQL();
        Scanner   scanner = new Scanner(session, ddl);
        ParserDQL parser  = new ParserDQL(session, scanner, null);

        parser.compileContext.setNextRangeVarIndex(0);
        parser.read();

        parser.isCheckOrTriggerCondition = true;

        Expression condition = parser.XreadBooleanValueExpression();

        return condition;
    }
