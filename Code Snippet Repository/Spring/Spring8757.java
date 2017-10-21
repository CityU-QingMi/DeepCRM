	@Test
	public void multipartRequestWithOptionalFileArrayNotPresent() throws Exception {
		byte[] json = "{\"name\":\"yeeeah\"}".getBytes(StandardCharsets.UTF_8);
		MockMultipartFile jsonPart = new MockMultipartFile("json", "json", "application/json", json);

		standaloneSetup(new MultipartController()).build()
				.perform(multipart("/optionalfilearray").file(jsonPart))
				.andExpect(status().isFound())
				.andExpect(model().attributeDoesNotExist("fileContent"))
				.andExpect(model().attribute("jsonContent", Collections.singletonMap("name", "yeeeah")));
	}
