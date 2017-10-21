	@SuppressWarnings("")
	private String decodeQueryParam(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		}
		catch (UnsupportedEncodingException ex) {
			if (logger.isWarnEnabled()) {
				logger.warn("Could not decode query param [" + value + "] as 'UTF-8'. " +
						"Falling back on default encoding; exception message: " + ex.getMessage());
			}
			return URLDecoder.decode(value);
		}
	}
