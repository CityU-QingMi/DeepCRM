	@Nullable
	private ZonedDateTime getFirstZonedDateTime(String headerName, boolean rejectInvalid) {
		String headerValue = getFirst(headerName);
		if (headerValue == null) {
			// No header value sent at all
			return null;
		}
		if (headerValue.length() >= 3) {
			// Short "0" or "-1" like values are never valid HTTP date headers...
			// Let's only bother with DateTimeFormatter parsing for long enough values.

			// See https://stackoverflow.com/questions/12626699/if-modified-since-http-header-passed-by-ie9-includes-length
			int parametersIndex = headerValue.indexOf(";");
			if (parametersIndex != -1) {
				headerValue = headerValue.substring(0, parametersIndex);
			}

			for (DateTimeFormatter dateFormatter : DATE_FORMATTERS) {
				try {
					return ZonedDateTime.parse(headerValue, dateFormatter);
				}
				catch (DateTimeParseException ex) {
					// ignore
				}
			}

		}
		if (rejectInvalid) {
			throw new IllegalArgumentException("Cannot parse date value \"" + headerValue +
					"\" for \"" + headerName + "\" header");
		}
		return null;
	}
