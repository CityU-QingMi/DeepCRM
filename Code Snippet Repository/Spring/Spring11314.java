	protected StringBuilder expandTargetUrlTemplate(String targetUrl,
			Map<String, Object> model, Map<String, String> uriVariables) {

		Matcher matcher = URI_TEMPLATE_VARIABLE_PATTERN.matcher(targetUrl);
		boolean found = matcher.find();
		if (!found) {
			return new StringBuilder(targetUrl);
		}
		StringBuilder result = new StringBuilder();
		int endLastMatch = 0;
		while (found) {
			String name = matcher.group(1);
			Object value = (model.containsKey(name) ? model.get(name) : uriVariables.get(name));
			Assert.notNull(value, "No value for URI variable '" + name + "'");
			result.append(targetUrl.substring(endLastMatch, matcher.start()));
			result.append(encodeUriVariable(value.toString()));
			endLastMatch = matcher.end();
			found = matcher.find();
		}
		result.append(targetUrl.substring(endLastMatch, targetUrl.length()));
		return result;
	}
