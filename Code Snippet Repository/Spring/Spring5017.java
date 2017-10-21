	@Test
	public void orderedParameterDiscoverers1() {
		PrioritizedParameterNameDiscoverer pnd = new PrioritizedParameterNameDiscoverer();
		pnd.addDiscoverer(returnsFooBar);
		assertTrue(Arrays.equals(FOO_BAR, pnd.getParameterNames(anyMethod)));
		assertTrue(Arrays.equals(FOO_BAR, pnd.getParameterNames((Constructor<?>) null)));
		pnd.addDiscoverer(returnsSomethingElse);
		assertTrue(Arrays.equals(FOO_BAR, pnd.getParameterNames(anyMethod)));
		assertTrue(Arrays.equals(FOO_BAR, pnd.getParameterNames((Constructor<?>) null)));
	}
