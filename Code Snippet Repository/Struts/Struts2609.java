    public Object getValue(EvaluationContext ctx) throws ELException {
        Object base = this.children[0].getValue(ctx);
        int propCount = this.jjtGetNumChildren();
        int i = 1;
        Object property = null;
        ELResolver resolver = ctx.getELResolver();
        while (base != null && i < propCount) {
            property = this.children[i].getValue(ctx);
            if (property == null) {
                return null;
            } else {
                ctx.setPropertyResolved(false);
                base = resolver.getValue(ctx, base, property);
            }
            i++;
        }
        return base;
    }
