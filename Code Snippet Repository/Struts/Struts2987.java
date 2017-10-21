	public Object getValue(ELContext context, Object base, Object property)
			throws NullPointerException, PropertyNotFoundException, ELException {
		if (context == null) {
			throw new NullPointerException();
		}

		if (base == null) {
			context.setPropertyResolved(true);
			if (property != null) {
				try {
					return this.variableResolver.resolveVariable(property
							.toString());
				} catch (javax.servlet.jsp.el.ELException e) {
					throw new ELException(e.getMessage(), e.getCause());
				}
			}
		}

		if (!context.isPropertyResolved()) {
			return DefaultResolver.getValue(context, base, property);
		}
		return null;
	}
