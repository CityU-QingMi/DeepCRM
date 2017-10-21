	private void params(MockHttpServletRequest request, UriComponents uriComponents) {
		uriComponents.getQueryParams().forEach((name, values) -> {
			String urlDecodedName = urlDecode(name);
			values.forEach(value -> {
				value = (value != null ? urlDecode(value) : "");
				request.addParameter(urlDecodedName, value);
			});
		});
		for (NameValuePair param : this.webRequest.getRequestParameters()) {
			request.addParameter(param.getName(), param.getValue());
		}
	}
