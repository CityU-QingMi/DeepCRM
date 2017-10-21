	@Test
	public void attributesAsNullValues() throws Exception {
		request.addParameter("name", "Patty");

		mavContainer.getModel().put("testBean1", null);
		mavContainer.getModel().put("testBean2", null);
		mavContainer.getModel().put("testBean3", null);

		assertNull(processor.resolveArgument(
				testBeanModelAttr, mavContainer, webRequest, binderFactory));

		assertNull(processor.resolveArgument(
				testBeanWithoutStringConstructorModelAttr, mavContainer, webRequest, binderFactory));

		Optional<TestBean> testBean = (Optional<TestBean>) processor.resolveArgument(
				testBeanWithOptionalModelAttr, mavContainer, webRequest, binderFactory);
		assertFalse(testBean.isPresent());
	}
