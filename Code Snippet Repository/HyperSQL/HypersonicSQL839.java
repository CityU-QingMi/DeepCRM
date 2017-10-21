    public Statement compileStatement(String sql) {

        parser.reset(this, sql);

        Statement cs =
            parser.compileStatement(ResultProperties.defaultPropsValue);

        cs.setCompileTimestamp(Long.MAX_VALUE);

        return cs;
    }
