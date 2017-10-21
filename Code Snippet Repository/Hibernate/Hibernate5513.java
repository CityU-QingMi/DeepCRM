	@Test
	public void testSetAndGetUnserializableProperty() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		try {
			MyObject object = new MyObject();
			object.value = 5;
			em.setProperty( "MyObject", object );
			assertFalse( em.getProperties().keySet().contains( "MyObject" ) );
		}
		finally {
			em.close();
		}
	}
