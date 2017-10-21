	@Test
	public void testProviderSerialization() throws Exception {
		ProviderTestBean testBean = beanFactory.getBean("providerTestBean", ProviderTestBean.class);
		Provider<?> provider = testBean.getProvider();

		provider = (Provider) SerializationTestUtils.serializeAndDeserialize(provider);

		Date date1 = (Date) provider.get();
		Date date2 = (Date) provider.get();
		assertTrue(date1 != date2);
	}
