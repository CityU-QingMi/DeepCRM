	@Test
	public void handleForm() throws Exception {

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("name", "George");
		formData.add("age", "5");

		assertEquals("Processed form: Foo[id=1, name='George', age=5]",
				performPost("/foos/1", MediaType.APPLICATION_FORM_URLENCODED, formData,
						MediaType.TEXT_PLAIN, String.class).getBody());
	}
