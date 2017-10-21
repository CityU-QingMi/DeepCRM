    private boolean isArithmeticExpression(Object tree, Map<String, Object> context) throws OgnlException {
        if (tree instanceof SimpleNode) {
            SimpleNode node = (SimpleNode) tree;
            OgnlContext ognlContext = null;

            if (context!=null && context instanceof OgnlContext) {
                ognlContext = (OgnlContext) context;
            }
            return node.isOperation(ognlContext);
        }
        return false;
    }
