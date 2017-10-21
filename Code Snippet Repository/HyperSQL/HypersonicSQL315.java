    private Statement compileExplainPlan() {

        Statement cs;

        read();
        readThis(Tokens.PLAN);
        readThis(Tokens.FOR);

        cs = compilePart(ResultProperties.defaultPropsValue);

        cs.setDescribe();

        return new StatementCommand(StatementTypes.EXPLAIN_PLAN,
                                    new Object[]{ cs });
    }
