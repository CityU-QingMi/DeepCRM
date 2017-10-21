	private void validateResult() throws Exception {
		String json = response.getContentAsString();
		DirectFieldAccessor viewAccessor = new DirectFieldAccessor(view);
		String jsonPrefix = (String)viewAccessor.getPropertyValue("jsonPrefix");
		if (jsonPrefix != null) {
			json = json.substring(5);
		}
		Object jsResult =
				jsContext.evaluateString(jsScope, "(" + json + ")", "JSON Stream", 1, null);
		assertNotNull("Json Result did not eval as valid JavaScript", jsResult);
		assertEquals("application/json", response.getContentType());
	}
