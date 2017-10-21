	@Test
	public void orderedParameterDiscoverers2() {
		PrioritizedParameterNameDiscoverer pnd = new PrioritizedParameterNameDiscoverer();
		pnd.addDiscoverer(returnsSomethingElse);
		assertTrue(Arrays.equals(SOMETHING_ELSE, pnd.getParameterNames(anyMethod)));
		assertTrue(Arrays.equals(SOMETHING_ELSE, pnd.getParameterNames((Constructor<?>) null)));
		pnd.addDiscoverer(returnsFooBar);
		assertTrue(Arrays.equals(SOMETHING_ELSE, pnd.getParameterNames(anyMethod)));
		assertTrue(Arrays.equals(SOMETHING_ELSE, pnd.getParameterNames((Constructor<?>) null)));
	}
