	@Test
	public void testSetAndGetSerializedProperty() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		try {
			em.setProperty( "MyObject", "Test123" );
			assertTrue( em.getProperties().keySet().contains( "MyObject" ) );
			assertEquals( "Test123", em.getProperties().get( "MyObject" ) );
		}
		finally {
			em.close();
		}
	}
