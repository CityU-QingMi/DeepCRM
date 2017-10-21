	@Test
	public void jsonPostForObjectWithJacksonTypeInfoList() throws URISyntaxException {
		List<ParentClass> list = new ArrayList<>();
		list.add(new Foo("foo"));
		list.add(new Bar("bar"));
		ParameterizedTypeReference<?> typeReference = new ParameterizedTypeReference<List<ParentClass>>() {};
		RequestEntity<List<ParentClass>> entity = RequestEntity
				.post(new URI(baseUrl + "/jsonpost"))
				.contentType(new MediaType("application", "json", StandardCharsets.UTF_8))
				.body(list, typeReference.getType());
		String content = template.exchange(entity, String.class).getBody();
		assertTrue(content.contains("\"type\":\"foo\""));
		assertTrue(content.contains("\"type\":\"bar\""));
	}
