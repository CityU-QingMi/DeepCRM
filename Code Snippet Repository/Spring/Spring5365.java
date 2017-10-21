	@Test
	public void assumeGroupWithMatchingActiveTestGroup() {
		setTestGroups(JMXMP);
		try {
			Assume.group(JMXMP);
		}
		catch (AssumptionViolatedException ex) {
			fail("assumption should NOT have failed");
		}
	}
