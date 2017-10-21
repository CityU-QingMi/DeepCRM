	@Nullable
	protected String getDefaultMessage(MessageSourceResolvable resolvable, Locale locale) {
		String defaultMessage = resolvable.getDefaultMessage();
		String[] codes = resolvable.getCodes();
		if (defaultMessage != null) {
			if (!ObjectUtils.isEmpty(codes) && defaultMessage.equals(codes[0])) {
				// Never format a code-as-default-message, even with alwaysUseMessageFormat=true
				return defaultMessage;
			}
			return renderDefaultMessage(defaultMessage, resolvable.getArguments(), locale);
		}
		return (!ObjectUtils.isEmpty(codes) ? getDefaultMessage(codes[0]) : null);
	}
