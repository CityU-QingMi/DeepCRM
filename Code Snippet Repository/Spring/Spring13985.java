	@Nullable
	protected final String getCallbackParam(ServerHttpRequest request) {
		String query = request.getURI().getQuery();
		MultiValueMap<String, String> params = UriComponentsBuilder.newInstance().query(query).build().getQueryParams();
		String value = params.getFirst("c");
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		String result = UriUtils.decode(value, StandardCharsets.UTF_8);
		return (CALLBACK_PARAM_PATTERN.matcher(result).matches() ? result : null);
	}
