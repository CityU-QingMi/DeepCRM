	@Test
	public void testIsNotIgnoredDoesntIgnoreUnspecifiedBeanMethods() throws Exception {
		final String beanKey = "myTestBean";
		MethodExclusionMBeanInfoAssembler assembler = new MethodExclusionMBeanInfoAssembler();
		Properties ignored = new Properties();
		ignored.setProperty(beanKey, "dontExposeMe,setSuperman");
		assembler.setIgnoredMethodMappings(ignored);
		Method method = JmxTestBean.class.getMethod("dontExposeMe");
		assertFalse(assembler.isNotIgnored(method, beanKey));
		// this bean does not have any ignored methods on it, so must obviously not be ignored...
		assertTrue(assembler.isNotIgnored(method, "someOtherBeanKey"));
	}
