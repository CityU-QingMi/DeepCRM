	protected final MultiValueMap<String, String> getParamsMultiValueMap(MockHttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		for (String name : params.keySet()) {
			if (params.get(name) != null) {
				for (String value : params.get(name)) {
					multiValueMap.add(name, value);
				}
			}
		}
		return multiValueMap;
	}
