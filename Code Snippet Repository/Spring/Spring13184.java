	@Test
	public void createAttributeRequestParameter() throws Exception {
		request.addParameter("testBean1", "Patty");

		// Type conversion from "Patty" to TestBean via TestBean(String) constructor
		TestBean testBean = (TestBean) processor.resolveArgument(
				testBeanModelAttr, mavContainer, webRequest, binderFactory);

		assertEquals("Patty", testBean.getName());
	}
