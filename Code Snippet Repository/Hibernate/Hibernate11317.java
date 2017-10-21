	@Test
	public void testBasicPersistAndAuditFetch() throws Exception {
		EntityManager entityManager = getOrCreateEntityManager();
		try {
			StrTestEntity e = new StrTestEntity( "Acme" );
			entityManager.getTransaction().begin();
			entityManager.persist( e );
			entityManager.getTransaction().commit();
			entityManager.clear();

			StrTestEntity rev1 = getAuditReader().find ( StrTestEntity.class, e.getId(), 1 );
			assertEquals( e, rev1 );
		}
		catch ( Exception e ) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			entityManager.close();
		}
	}
