    private final Object invokeTarget(EvaluationContext ctx, Object target,
            Object[] paramValues) throws ELException {
        if (target instanceof MethodExpression) {
            MethodExpression me = (MethodExpression) target;
            return me.invoke(ctx.getELContext(), paramValues);
        } else if (target == null) {
            throw new MethodNotFoundException("Identity '" + this.image
                    + "' was null and was unable to invoke");
        } else {
            throw new ELException(
                    "Identity '"
                            + this.image
                            + "' does not reference a MethodExpression instance, returned type: "
                            + target.getClass().getName());
        }
    }
