	public void setValue(ELContext context, Object base, Object property,
			Object value) throws NullPointerException,
			PropertyNotFoundException, PropertyNotWritableException,
			ELException {
		if (context == null) {
			throw new NullPointerException();
		}

		if (base == null) {
			context.setPropertyResolved(true);
			throw new PropertyNotWritableException(
					"Legacy VariableResolver wrapped, not writable");
		}

		if (!context.isPropertyResolved()) {
			DefaultResolver.setValue(context, base, property, value);
		}
	}
