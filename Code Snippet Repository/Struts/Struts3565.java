	private void setupParamStub(Map<String, String[]> requestParams,
			Mock mockRequest, String method) {
		Map<String, String> newMap = new HashMap<String, String>();
		for (String key : requestParams.keySet()) {
			String[] val = requestParams.get(key);
			newMap.put(key, val[0]);
		}
		setupStub(newMap, mockRequest, method);

	}
