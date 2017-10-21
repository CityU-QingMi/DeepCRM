	private PathPattern.PathMatchInfo checkCapture(String uriTemplate, String path, String... keyValues) {
		PathPatternParser parser = new PathPatternParser();
		PathPattern pattern = parser.parse(uriTemplate);
		PathPattern.PathMatchInfo matchResult = pattern.matchAndExtract(toPathContainer(path));
		Map<String, String> expectedKeyValues = new HashMap<>();
		for (int i = 0; i < keyValues.length; i += 2) {
			expectedKeyValues.put(keyValues[i], keyValues[i + 1]);
		}
		for (Map.Entry<String, String> me : expectedKeyValues.entrySet()) {
			String value = matchResult.getUriVariables().get(me.getKey());
			if (value == null) {
				fail("Did not find key '" + me.getKey() + "' in captured variables: "
						+ matchResult.getUriVariables());
			}
			if (!value.equals(me.getValue())) {
				fail("Expected value '" + me.getValue() + "' for key '" + me.getKey()
						+ "' but was '" + value + "'");
			}
		}
		return matchResult;
	}
