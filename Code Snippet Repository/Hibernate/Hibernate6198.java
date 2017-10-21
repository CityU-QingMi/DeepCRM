	@Test
	public void testEmbeddedId() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Umbrella umbrella = new Umbrella();
			umbrella.setId( new Umbrella.PK() );
			umbrella.getId().setBrand( "Burberry" );
			umbrella.getId().setModel( "Red Hat" );
			em.persist( umbrella );
			em.flush();
			assertEquals(
					umbrella.getId(),
					em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier( umbrella )
			);
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
