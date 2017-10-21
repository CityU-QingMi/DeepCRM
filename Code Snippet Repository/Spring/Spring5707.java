	private void writeProperty(
			TypedValue contextObject, EvaluationContext evalContext, String name, @Nullable Object newValue)
			throws EvaluationException {

		if (contextObject.getValue() == null && this.nullSafe) {
			return;
		}
		if (contextObject.getValue() == null) {
			throw new SpelEvaluationException(getStartPosition(), SpelMessage.PROPERTY_OR_FIELD_NOT_WRITABLE_ON_NULL, name);
		}

		PropertyAccessor accessorToUse = this.cachedWriteAccessor;
		if (accessorToUse != null) {
			if (evalContext.getPropertyAccessors().contains(accessorToUse)) {
				try {
					accessorToUse.write(evalContext, contextObject.getValue(), name, newValue);
					return;
				}
				catch (Exception ex) {
					// This is OK - it may have gone stale due to a class change,
					// let's try to get a new one and call it before giving up...
				}
			}
			this.cachedWriteAccessor = null;
		}

		List<PropertyAccessor> accessorsToTry =
				getPropertyAccessorsToTry(contextObject.getValue(), evalContext.getPropertyAccessors());
		try {
			for (PropertyAccessor accessor : accessorsToTry) {
				if (accessor.canWrite(evalContext, contextObject.getValue(), name)) {
					this.cachedWriteAccessor = accessor;
					accessor.write(evalContext, contextObject.getValue(), name, newValue);
					return;
				}
			}
		}
		catch (AccessException ex) {
			throw new SpelEvaluationException(getStartPosition(), ex, SpelMessage.EXCEPTION_DURING_PROPERTY_WRITE,
					name, ex.getMessage());
		}

		throw new SpelEvaluationException(getStartPosition(), SpelMessage.PROPERTY_OR_FIELD_NOT_WRITABLE, name,
				FormatHelper.formatClassNameForMessage(getObjectClass(contextObject.getValue())));
	}
