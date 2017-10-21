	@Test
	public void partsBinding() {
		PartsBean bean = new PartsBean();
		partsServlet.setBean(bean);

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		Resource firstPart = new ClassPathResource("/org/springframework/http/converter/logo.jpg");
		parts.add("firstPart", firstPart);
		parts.add("secondPart", "secondValue");

		template.postForLocation(baseUrl + "/parts", parts);

		assertNotNull(bean.getFirstPart());
		assertNotNull(bean.getSecondPart());
	}
