	@Test
	public void jsonPostForObject() throws URISyntaxException {
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		MySampleBean bean = new MySampleBean();
		bean.setWith1("with");
		bean.setWith2("with");
		bean.setWithout("without");
		HttpEntity<MySampleBean> entity = new HttpEntity<>(bean, entityHeaders);
		String s = template.postForObject(baseUrl + "/jsonpost", entity, String.class);
		assertTrue(s.contains("\"with1\":\"with\""));
		assertTrue(s.contains("\"with2\":\"with\""));
		assertTrue(s.contains("\"without\":\"without\""));
	}
