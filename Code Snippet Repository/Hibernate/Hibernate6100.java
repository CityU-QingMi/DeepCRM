	@Test
	@TestForIssue(jiraKey = "")
	public void testCacheStoreAndRetrieveModeParameter() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Query query = em.createQuery( "select item from Item item" );

			query.getHints().clear();

			query.setHint( "javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE );
			query.setHint( "javax.persistence.cache.storeMode", CacheStoreMode.REFRESH );

			assertEquals( CacheRetrieveMode.USE, query.getHints().get( "javax.persistence.cache.retrieveMode" ) );
			assertEquals( CacheStoreMode.REFRESH, query.getHints().get( "javax.persistence.cache.storeMode" ) );

			query.getHints().clear();

			query.setHint( "javax.persistence.cache.retrieveMode", "USE" );
			query.setHint( "javax.persistence.cache.storeMode", "REFRESH" );

			assertEquals( CacheRetrieveMode.USE, query.getHints().get( "javax.persistence.cache.retrieveMode" ) );
			assertEquals( CacheStoreMode.REFRESH, query.getHints().get( "javax.persistence.cache.storeMode" ) );

			em.getTransaction().commit();
		}
		catch (Exception e){
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
	}
