    public MethodExpressionImpl(String expr, Node node,
            FunctionMapper fnMapper, VariableMapper varMapper,
            Class expectedType, Class[] paramTypes) {
        super();
        this.expr = expr;
        this.node = node;
        this.fnMapper = fnMapper;
        this.varMapper = varMapper;
        this.expectedType = expectedType;
        this.paramTypes = paramTypes;
    }
