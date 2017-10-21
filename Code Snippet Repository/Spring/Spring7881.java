	protected void checkInvariants(AbstractEntityManagerFactoryBean demf) {
		assertTrue(EntityManagerFactory.class.isAssignableFrom(demf.getObjectType()));
		Object gotObject = demf.getObject();
		assertTrue("Object created by factory implements EntityManagerFactoryInfo",
				gotObject instanceof EntityManagerFactoryInfo);
		EntityManagerFactoryInfo emfi = (EntityManagerFactoryInfo) demf.getObject();
		assertSame("Successive invocations of getObject() return same object", emfi, demf.getObject());
		assertSame(emfi, demf.getObject());
		assertSame(emfi.getNativeEntityManagerFactory(), mockEmf);
	}
