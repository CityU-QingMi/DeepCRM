	@Test
	public void partListBinding() {
		PartListBean bean = new PartListBean();
		partListServlet.setBean(bean);

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("partList", "first value");
		parts.add("partList", "second value");
		Resource logo = new ClassPathResource("/org/springframework/http/converter/logo.jpg");
		parts.add("partList", logo);

		template.postForLocation(baseUrl + "/partlist", parts);

		assertNotNull(bean.getPartList());
		assertEquals(parts.get("partList").size(), bean.getPartList().size());
	}
