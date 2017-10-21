	@Test
	public void multipartRequestWithOptionalFileArray() throws Exception {
		byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
		MockMultipartFile filePart1 = new MockMultipartFile("file", "orig", null, fileContent);
		MockMultipartFile filePart2 = new MockMultipartFile("file", "orig", null, fileContent);

		byte[] json = "{\"name\":\"yeeeah\"}".getBytes(StandardCharsets.UTF_8);
		MockMultipartFile jsonPart = new MockMultipartFile("json", "json", "application/json", json);

		standaloneSetup(new MultipartController()).build()
				.perform(multipart("/optionalfilearray").file(filePart1).file(filePart2).file(jsonPart))
				.andExpect(status().isFound())
				.andExpect(model().attribute("fileContent", fileContent))
				.andExpect(model().attribute("jsonContent", Collections.singletonMap("name", "yeeeah")));
	}
