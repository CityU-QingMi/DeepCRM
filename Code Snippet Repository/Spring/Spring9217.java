	public static List<MediaType> parseMediaTypes(@Nullable String mediaTypes) {
		if (!StringUtils.hasLength(mediaTypes)) {
			return Collections.emptyList();
		}
		String[] tokens = StringUtils.tokenizeToStringArray(mediaTypes, ",");
		List<MediaType> result = new ArrayList<>(tokens.length);
		for (String token : tokens) {
			result.add(parseMediaType(token));
		}
		return result;
	}
