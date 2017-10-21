    public Result executeDirectStatement(String sql) {

        try {
            Statement cs = compileStatement(sql);
            Result result = executeCompiledStatement(cs,
                ValuePool.emptyObjectArray, 0);

            return result;
        } catch (HsqlException e) {
            return Result.newErrorResult(e);
        }
    }
