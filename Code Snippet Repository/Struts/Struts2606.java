    public Object getValue(EvaluationContext ctx)
            throws ELException {
        Object obj = this.children[0].getValue(ctx);
        Boolean b = coerceToBoolean(obj);
        if (b.booleanValue()) {
            return b;
        }
        obj = this.children[1].getValue(ctx);
        b = coerceToBoolean(obj);
        return b;
    }
