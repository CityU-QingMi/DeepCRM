    public void setValue(EvaluationContext ctx, Object value)
            throws ELException {
        Target t = getTarget(ctx);
        ctx.setPropertyResolved(false);
        ELResolver resolver = ctx.getELResolver();

        // coerce to the expected type
        Class<?> targetClass = resolver.getType(ctx, t.base, t.property);
        if (COERCE_TO_ZERO == true
                || !isAssignable(value, targetClass)) {
            value = ELSupport.coerceToType(value, targetClass);
        }
        resolver.setValue(ctx, t.base, t.property, value);
    }
