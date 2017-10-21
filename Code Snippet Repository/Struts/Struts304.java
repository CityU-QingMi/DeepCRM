    public void setValue(final String name, final Map<String, Object> context, final Object root, final Object value) throws OgnlException {
        compileAndExecute(name, context, new OgnlTask<Void>() {
            public Void execute(Object tree) throws OgnlException {
                if (isEvalExpression(tree, context)) {
                    throw new OgnlException("Eval expression/chained expressions cannot be used as parameter name");
                }
                if (isArithmeticExpression(tree, context)) {
                    throw new OgnlException("Arithmetic expressions cannot be used as parameter name");
                }
                Ognl.setValue(tree, context, root, value);
                return null;
            }
        });
    }
