	public void addHeader(String name, Object value) {
		if (HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(name) &&
				!this.headers.containsKey(HttpHeaders.CONTENT_TYPE)) {

			setContentType(value.toString());
		}
		else if (HttpHeaders.ACCEPT_LANGUAGE.equalsIgnoreCase(name) &&
				!this.headers.containsKey(HttpHeaders.ACCEPT_LANGUAGE)) {

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.ACCEPT_LANGUAGE, value.toString());
			setPreferredLocales(headers.getAcceptLanguageAsLocales());
		}
		else {
			doAddHeaderValue(name, value, false);
		}
	}
