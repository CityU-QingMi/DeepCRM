    public Object getValue(EvaluationContext ctx) throws ELException {
        VariableMapper varMapper = ctx.getVariableMapper();
        if (varMapper != null) {
            ValueExpression expr = varMapper.resolveVariable(this.image);
            if (expr != null) {
                return expr.getValue(ctx.getELContext());
            }
        }
        ctx.setPropertyResolved(false);
        return ctx.getELResolver().getValue(ctx, null, this.image);
    }
