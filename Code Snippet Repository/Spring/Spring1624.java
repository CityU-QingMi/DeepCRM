	@Test
	public void mergeMapWithInnerBeanAsMapEntryValue() throws Exception {
		TestBean bean = (TestBean) this.beanFactory.getBean("childWithMapOfRefs");
		Map map = bean.getSomeMap();
		assertNotNull(map);
		assertEquals(2, map.size());
		assertNotNull(map.get("Rob"));
		assertTrue(map.get("Rob") instanceof TestBean);
		assertEquals("Sally", ((TestBean) map.get("Rob")).getName());
	}
