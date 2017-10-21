	private long parseDateHeader(String headerName) {
		long dateValue = -1;
		try {
			dateValue = getRequest().getDateHeader(headerName);
		}
		catch (IllegalArgumentException ex) {
			String headerValue = getHeader(headerName);
			// Possibly an IE 10 style value: "Wed, 09 Apr 2014 09:57:42 GMT; length=13774"
			if (headerValue != null) {
				int separatorIndex = headerValue.indexOf(';');
				if (separatorIndex != -1) {
					String datePart = headerValue.substring(0, separatorIndex);
					dateValue = parseDateValue(datePart);
				}
			}
		}
		return dateValue;
	}
