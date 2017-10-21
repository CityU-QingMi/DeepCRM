	@Override
	public final String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
		String msg = getMessageInternal(code, args, locale);
		if (msg != null) {
			return msg;
		}
		if (defaultMessage == null) {
			String fallback = getDefaultMessage(code);
			return (fallback != null ? fallback : "");
		}
		return renderDefaultMessage(defaultMessage, args, locale);
	}
