	@Test
	public void multipartRequestWithSingleFile() throws Exception {
		byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
		MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);

		byte[] json = "{\"name\":\"yeeeah\"}".getBytes(StandardCharsets.UTF_8);
		MockMultipartFile jsonPart = new MockMultipartFile("json", "json", "application/json", json);

		standaloneSetup(new MultipartController()).build()
				.perform(multipart("/multipartfile").file(filePart).file(jsonPart))
				.andExpect(status().isFound())
				.andExpect(model().attribute("fileContent", fileContent))
				.andExpect(model().attribute("jsonContent", Collections.singletonMap("name", "yeeeah")));
	}
