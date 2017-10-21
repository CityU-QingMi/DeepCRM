	private String extractSessionId(HttpHeaders headers) {
		List<String> headerValues = headers.get("Set-Cookie");
		assertNotNull(headerValues);
		assertEquals(1, headerValues.size());

		for (String s : headerValues.get(0).split(";")){
			if (s.startsWith("SESSION=")) {
				return s.substring("SESSION=".length());
			}
		}
		return null;
	}
