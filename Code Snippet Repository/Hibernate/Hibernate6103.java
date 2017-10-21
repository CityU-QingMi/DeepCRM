	@Test
	public void testNativeQueryByEntity() {
		Item item = new Item( "Mouse", "Micro$oft mouse" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( item );
			assertTrue( em.contains( item ) );
			em.getTransaction().commit();

			Statistics stats = em.getEntityManagerFactory().unwrap( SessionFactoryImplementor.class ).getStatistics();
			stats.clear();
			assertEquals( 0, stats.getFlushCount() );

			em.getTransaction().begin();
			item = (Item) em.createNativeQuery( "select * from Item", Item.class ).getSingleResult();
			assertEquals( 1, stats.getFlushCount() );
			assertNotNull( item );
			assertEquals( "Micro$oft mouse", item.getDescr() );
			em.remove( item );
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
