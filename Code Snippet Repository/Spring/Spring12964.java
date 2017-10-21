	private void testCreate(String url, String basename) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("json-data", new HttpEntity<>(new TestData(basename)));
		parts.add("file-data", new ClassPathResource("logo.jpg", getClass()));
		parts.add("empty-data", new HttpEntity<>(new byte[0])); // SPR-12860

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream", StandardCharsets.ISO_8859_1));
		parts.add("iso-8859-1-data", new HttpEntity<>(new byte[] {(byte) 0xC4}, headers)); // SPR-13096

		URI location = restTemplate.postForLocation(url, parts);
		assertEquals("http://localhost:8080/test/" + basename + "/logo.jpg", location.toString());
	}
