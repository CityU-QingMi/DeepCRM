    @Test
	public void testInvalidOrm1() {
		PersistenceUnitInfoImpl pui = new PersistenceUnitInfoImpl( "invalid-orm1-test", "1.0" )
				.addMappingFileName( "org/hibernate/jpa/test/jee/invalid-orm-1.xml" );
		HibernatePersistenceProvider hp = new HibernatePersistenceProvider();
		EntityManagerFactory emf = null;
		try {
			emf = hp.createContainerEntityManagerFactory(
					pui,
					Collections.EMPTY_MAP
			);
			Assert.fail( "expecting 'invalid content' error" );
		}
		catch (InvalidMappingException | AnnotationException expected) {
			// expected condition
		}
		catch (PersistenceException expected) {
			// expected condition
		}
		finally {
			if ( emf != null ) {
				emf.close();
			}
		}
	}
