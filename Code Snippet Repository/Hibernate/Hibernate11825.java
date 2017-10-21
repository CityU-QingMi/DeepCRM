	@Test
	public void testExtensionPoints() throws Exception {
		final ServiceReference sr = bundleContext.getServiceReference( SessionFactory.class.getName() );
		final SessionFactoryImplementor sfi = (SessionFactoryImplementor) bundleContext.getService( sr );

		assertTrue( TestIntegrator.passed() );

		Class impl = sfi.getServiceRegistry().getService( StrategySelector.class ).selectStrategyImplementor( Calendar.class, TestStrategyRegistrationProvider.GREGORIAN );
		assertNotNull( impl );

		BasicType basicType = sfi.getTypeResolver().basic( TestTypeContributor.NAME );
		assertNotNull( basicType );
	}
