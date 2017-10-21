    private <T> Object compileAndExecute(String expression, Map<String, Object> context, OgnlTask<T> task) throws OgnlException {
        Object tree;
        if (enableExpressionCache) {
            tree = expressions.get(expression);
            if (tree == null) {
                tree = Ognl.parseExpression(expression);
                checkEnableEvalExpression(tree, context);
            }
        } else {
            tree = Ognl.parseExpression(expression);
            checkEnableEvalExpression(tree, context);
        }

        final T exec = task.execute(tree);
        // if cache is enabled and it's a valid expression, puts it in
        if(enableExpressionCache) {
            expressions.putIfAbsent(expression, tree);
        }
        return exec;
    }
