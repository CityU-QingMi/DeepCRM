	public Expression parseExpression(String expression, Class expectedType,
			FunctionMapper fMapper) throws ELException {
		try {
			ELContextImpl ctx = new ELContextImpl(ELResolverImpl.DefaultResolver);
            if (fMapper != null) {
                ctx.setFunctionMapper(new FunctionMapperImpl(fMapper));
            }
			ValueExpression ve = this.factory.createValueExpression(ctx, expression, expectedType);
			return new ExpressionImpl(ve);
		} catch (javax.el.ELException e) {
			throw new ELParseException(e.getMessage());
		}
	}
