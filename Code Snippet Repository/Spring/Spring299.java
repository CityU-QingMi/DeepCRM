	@Test
	public void testRejectsPerCflowBelowAspect() {
		try {
			getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(new PerCflowBelowAspect(),"someBean"));
			fail("Cannot accept cflowbelow");
		}
		catch (AopConfigException ex) {
			assertTrue(ex.getMessage().indexOf("PERCFLOWBELOW") != -1);
		}
	}
