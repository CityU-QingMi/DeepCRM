	@Override
	@Nullable
	public Object getFieldValue(String field) {
		FieldError fieldError = getFieldError(field);
		// Use rejected value in case of error, current bean property value else.
		Object value = (fieldError != null ? fieldError.getRejectedValue() :
				getActualFieldValue(fixedField(field)));
		// Apply formatting, but not on binding failures like type mismatches.
		if (fieldError == null || !fieldError.isBindingFailure()) {
			value = formatFieldValue(field, value);
		}
		return value;
	}
