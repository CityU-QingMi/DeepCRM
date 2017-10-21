	@Test
	public void attributesAsOptionalEmpty() throws Exception {
		request.addParameter("name", "Patty");

		mavContainer.getModel().put("testBean1", Optional.empty());
		mavContainer.getModel().put("testBean2", Optional.empty());
		mavContainer.getModel().put("testBean3", Optional.empty());

		assertNull(processor.resolveArgument(
				testBeanModelAttr, mavContainer, webRequest, binderFactory));

		assertNull(processor.resolveArgument(
				testBeanWithoutStringConstructorModelAttr, mavContainer, webRequest, binderFactory));

		Optional<TestBean> testBean =(Optional<TestBean>) processor.resolveArgument(
				testBeanWithOptionalModelAttr, mavContainer, webRequest, binderFactory);
		assertFalse(testBean.isPresent());
	}
