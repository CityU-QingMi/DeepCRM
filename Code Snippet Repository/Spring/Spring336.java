	@Test
	public void testCompleteProxiedInterfacesAdvisedIncluded() {
		AdvisedSupport as = new AdvisedSupport();
		as.addInterface(ITestBean.class);
		as.addInterface(Comparable.class);
		as.addInterface(Advised.class);
		Class<?>[] completedInterfaces = AopProxyUtils.completeProxiedInterfaces(as);
		assertEquals(4, completedInterfaces.length);

		// Can't assume ordering for others, so use a list
		List<?> l = Arrays.asList(completedInterfaces);
		assertTrue(l.contains(Advised.class));
		assertTrue(l.contains(ITestBean.class));
		assertTrue(l.contains(Comparable.class));
	}
