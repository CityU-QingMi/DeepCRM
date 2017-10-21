	@Test
	public void testRejectsPerCflowAspect() {
		try {
			getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(new PerCflowAspect(),"someBean"));
			fail("Cannot accept cflow");
		}
		catch (AopConfigException ex) {
			assertTrue(ex.getMessage().indexOf("PERCFLOW") != -1);
		}
	}
