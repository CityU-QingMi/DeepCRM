	public boolean isWritableProperty(String name, TypedValue contextObject, EvaluationContext evalContext)
			throws EvaluationException {

		Object value = contextObject.getValue();
		if (value != null) {
			List<PropertyAccessor> accessorsToTry =
					getPropertyAccessorsToTry(contextObject.getValue(), evalContext.getPropertyAccessors());
			for (PropertyAccessor accessor : accessorsToTry) {
				try {
					if (accessor.canWrite(evalContext, value, name)) {
						return true;
					}
				}
				catch (AccessException ex) {
					// let others try
				}
			}
		}
		return false;
	}
