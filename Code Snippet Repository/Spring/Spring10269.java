	@Test
	public void getFormParts() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<MultiValueMap<String, Object>> request = RequestEntity
				.post(new URI("http://localhost:" + port + "/form-parts"))
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(generateBody());
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
