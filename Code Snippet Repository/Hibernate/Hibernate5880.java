    @Test
	public void testOrm1() {
		PersistenceUnitInfoImpl pui = new PersistenceUnitInfoImpl( "orm1-test", "1.0" )
				.addMappingFileName( "org/hibernate/jpa/test/jee/valid-orm-1.xml" );
		HibernatePersistenceProvider hp = new HibernatePersistenceProvider();
		EntityManagerFactory emf = hp.createContainerEntityManagerFactory( pui, Collections.EMPTY_MAP );
		try {
			emf.getMetamodel().entity( Lighter1.class ); // exception if not entity
		}
		finally {
			emf.close();
		}
	}
