	public static Object proprietaryEvaluate(final String expression,
			final Class expectedType, final PageContext pageContext,
			final ProtectedFunctionMapper functionMap, final boolean escape)
			throws ELException {
		Object retValue;
        final ExpressionFactory exprFactory = jspf.getJspApplicationContext(pageContext.getServletContext()).getExpressionFactory();
		if (SecurityUtil.isPackageProtectionEnabled()) {
			try {
				retValue = AccessController
						.doPrivileged(new PrivilegedExceptionAction() {

							public Object run() throws Exception {
                                ELContextImpl ctx = (ELContextImpl) pageContext.getELContext();
                                ctx.setFunctionMapper(new FunctionMapperImpl(functionMap));
								ValueExpression ve = exprFactory.createValueExpression(ctx, expression, expectedType);
                                return ve.getValue(ctx);
							}
						});
			} catch (PrivilegedActionException ex) {
				Exception realEx = ex.getException();
				if (realEx instanceof ELException) {
					throw (ELException) realEx;
				} else {
					throw new ELException(realEx);
				}
			}
		} else {
            ELContextImpl ctx = (ELContextImpl) pageContext.getELContext();
            ctx.setFunctionMapper(new FunctionMapperImpl(functionMap));
            ValueExpression ve = exprFactory.createValueExpression(ctx, expression, expectedType);
            retValue = ve.getValue(ctx);
		}
		if (escape && retValue != null) {
			retValue = XmlEscape(retValue.toString());
		}

		return retValue;
	}
