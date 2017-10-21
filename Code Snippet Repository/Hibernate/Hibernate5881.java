    @Test
	public void testOrm2() {
		PersistenceUnitInfoImpl pui = new PersistenceUnitInfoImpl( "orm2-test", "2.0" )
				.addMappingFileName( "org/hibernate/jpa/test/jee/valid-orm-2.xml" );
		HibernatePersistenceProvider hp = new HibernatePersistenceProvider();
		EntityManagerFactory emf = hp.createContainerEntityManagerFactory( pui, Collections.EMPTY_MAP );
		try {
			emf.getMetamodel().entity( Lighter.class ); // exception if not entity
		}
		finally {
			emf.close();
		}
	}
