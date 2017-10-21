	private MultiValueMap<String, Object> generateBody() {

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("fieldPart", "fieldValue");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		ClassPathResource resource = new ClassPathResource("foo.txt", MultipartHttpMessageReader.class);
		parts.add("fileParts", new HttpEntity<>(resource, headers));

		headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		resource = new ClassPathResource("logo.png", getClass());
		parts.add("fileParts", new HttpEntity<>(resource, headers));

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		parts.add("jsonPart", new HttpEntity<>(new Person("Jason"), headers));

		return parts;
	}
