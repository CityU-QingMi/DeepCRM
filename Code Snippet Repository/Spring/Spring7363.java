	@SuppressWarnings("")
	@Nullable
	public static String getFirstNativeHeader(String headerName, Map<String, Object> headers) {
		Map<String, List<String>> map = (Map<String, List<String>>) headers.get(NATIVE_HEADERS);
		if (map != null) {
			List<String> values = map.get(headerName);
			if (values != null) {
				return values.get(0);
			}
		}
		return null;
	}
