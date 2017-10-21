	@SuppressWarnings("")
	@Nullable
	private <T extends ObjectError> T escapeObjectError(@Nullable T source) {
		if (source == null) {
			return null;
		}
		String defaultMessage = source.getDefaultMessage();
		if (defaultMessage != null) {
			defaultMessage = HtmlUtils.htmlEscape(defaultMessage);
		}
		if (source instanceof FieldError) {
			FieldError fieldError = (FieldError) source;
			Object value = fieldError.getRejectedValue();
			if (value instanceof String) {
				value = HtmlUtils.htmlEscape((String) value);
			}
			return (T) new FieldError(
					fieldError.getObjectName(), fieldError.getField(), value, fieldError.isBindingFailure(),
					fieldError.getCodes(), fieldError.getArguments(), defaultMessage);
		}
		else {
			return (T) new ObjectError(
					source.getObjectName(), source.getCodes(), source.getArguments(), defaultMessage);
		}
	}
