    private <T> Object compileAndExecuteMethod(String expression, Map<String, Object> context, OgnlTask<T> task) throws OgnlException {
        Object tree;
        if (enableExpressionCache) {
            tree = expressions.get(expression);
            if (tree == null) {
                tree = Ognl.parseExpression(expression);
                checkSimpleMethod(tree, context);
            }
        } else {
            tree = Ognl.parseExpression(expression);
            checkSimpleMethod(tree, context);
        }

        final T exec = task.execute(tree);
        // if cache is enabled and it's a valid expression, puts it in
        if(enableExpressionCache) {
            expressions.putIfAbsent(expression, tree);
        }
        return exec;
    }
