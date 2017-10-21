	private boolean setSpecialHeader(String name, Object value) {
		if (HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(name)) {
			setContentType(value.toString());
			return true;
		}
		else if (HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(name)) {
			setContentLength(value instanceof Number ? ((Number) value).intValue() :
					Integer.parseInt(value.toString()));
			return true;
		}
		else if (HttpHeaders.ACCEPT_LANGUAGE.equalsIgnoreCase(name)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.ACCEPT_LANGUAGE, value.toString());
			List<Locale> locales = headers.getAcceptLanguageAsLocales();
			setLocale(locales.isEmpty() ? null : locales.get(0));
			return true;
		}
		else {
			return false;
		}
	}
