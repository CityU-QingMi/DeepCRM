	@Override
	public String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
		if (this.parentMessageSource != null) {
			return this.parentMessageSource.getMessage(code, args, defaultMessage, locale);
		}
		else if (defaultMessage != null) {
			return renderDefaultMessage(defaultMessage, args, locale);
		}
		else {
			return "";
		}
	}
