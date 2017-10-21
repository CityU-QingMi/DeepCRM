    public Object getValue(ELContext context) throws PropertyNotFoundException,
            ELException {
        EvaluationContext ctx = new EvaluationContext(context, this.fnMapper,
                this.varMapper);
        Object value = this.getNode().getValue(ctx);
        if (this.expectedType != null) {
            return ELSupport.coerceToType(value, this.expectedType);
        }
        return value;
    }
