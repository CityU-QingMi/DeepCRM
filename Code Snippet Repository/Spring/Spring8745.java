	@Test
	public void multipartRequestWithServletParts() throws Exception {
		byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
		MockPart filePart = new MockPart("file", "orig", fileContent);

		byte[] json = "{\"name\":\"yeeeah\"}".getBytes(StandardCharsets.UTF_8);
		MockPart jsonPart = new MockPart("json", "json", json);
		jsonPart.getHeaders().setContentType(MediaType.APPLICATION_JSON);

		standaloneSetup(new MultipartController()).build()
				.perform(multipart("/multipartfile").part(filePart).part(jsonPart))
				.andExpect(status().isFound())
				.andExpect(model().attribute("fileContent", fileContent))
				.andExpect(model().attribute("jsonContent", Collections.singletonMap("name", "yeeeah")));
	}
