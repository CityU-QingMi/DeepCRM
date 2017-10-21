	public static List<WebSocketExtension> parseExtensions(String extensions) {
		if (StringUtils.hasText(extensions)) {
			String[] tokens = StringUtils.tokenizeToStringArray(extensions, ",");
			List<WebSocketExtension> result = new ArrayList<>(tokens.length);
			for (String token : tokens) {
				result.add(parseExtension(token));
			}
			return result;
		}
		else {
			return Collections.emptyList();
		}
	}
