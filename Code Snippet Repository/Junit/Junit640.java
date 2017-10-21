	@Override
	public URI convert(String value) {
		try {
			return new URI(value);
		}
		catch (URISyntaxException e) {
			String message = MessageFormat.format("Value [{0}] is not a valid URI", value);
			throw new ValueConversionException(message, e);
		}
	}
