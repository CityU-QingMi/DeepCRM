	public Class<?> getType(ELContext context, Object base, Object property)
			throws NullPointerException, PropertyNotFoundException, ELException {
		if (context == null) {
			throw new NullPointerException();
		}

		if (base == null) {
			context.setPropertyResolved(true);
			if (property != null) {
				try {
					Object obj = this.variableResolver.resolveVariable(property
							.toString());
					return (obj != null) ? obj.getClass() : null;
				} catch (javax.servlet.jsp.el.ELException e) {
					throw new ELException(e.getMessage(), e.getCause());
				}
			}
		}

		if (!context.isPropertyResolved()) {
			return DefaultResolver.getType(context, base, property);
		}
		return null;
	}
