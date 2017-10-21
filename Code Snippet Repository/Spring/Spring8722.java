	@Test
	public void stringEncodingDetection() throws Exception {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
				"<person><name>Jürgen</name></person>";
		byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
		MockHttpServletResponse response = new MockHttpServletResponse();
		response.addHeader("Content-Type", "application/xml");
		StreamUtils.copy(bytes, response.getOutputStream());
		StubMvcResult result = new StubMvcResult(null, null, null, null, null, null, response);

		new XpathResultMatchers("/person/name", null).string("Jürgen").match(result);
	}
