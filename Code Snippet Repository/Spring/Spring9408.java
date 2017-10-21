	private static PathSegment parsePathSegment(String segment) {
		Charset charset = StandardCharsets.UTF_8;
		int index = segment.indexOf(';');
		if (index == -1) {
			String valueToMatch = StringUtils.uriDecode(segment, charset);
			return new DefaultPathSegment(segment, valueToMatch, EMPTY_MAP);
		}
		else {
			String valueToMatch = StringUtils.uriDecode(segment.substring(0, index), charset);
			String pathParameterContent = segment.substring(index);
			MultiValueMap<String, String> parameters = parsePathParams(pathParameterContent, charset);
			return new DefaultPathSegment(segment, valueToMatch, parameters);
		}
	}
