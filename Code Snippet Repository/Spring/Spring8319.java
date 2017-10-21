	private String statusMessage(int statusCode) {
		String errorMessage = this.response.getErrorMessage();
		if (StringUtils.hasText(errorMessage)) {
			return errorMessage;
		}

		try {
			return HttpStatus.valueOf(statusCode).getReasonPhrase();
		}
		catch (IllegalArgumentException ex) {
			// ignore
		}

		return DEFAULT_STATUS_MESSAGE;
	}
