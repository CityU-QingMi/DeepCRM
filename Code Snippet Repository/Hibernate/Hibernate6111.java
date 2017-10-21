	@Test
	public void testNativeQueryWithPositionalParameter() {
		Item item = new Item( "Mouse", "Micro$oft mouse" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( item );
			assertTrue( em.contains( item ) );
			em.getTransaction().commit();

			em.getTransaction().begin();
			Query query = em.createNativeQuery( "select * from Item where name = ?1", Item.class );
			query.setParameter( 1, "Mouse" );
			item = (Item) query.getSingleResult();
			assertNotNull( item );
			assertEquals( "Micro$oft mouse", item.getDescr() );
			query = em.createNativeQuery( "select * from Item where name = ?", Item.class );
			query.setParameter( 1, "Mouse" );
			item = (Item) query.getSingleResult();
			assertNotNull( item );
			assertEquals( "Micro$oft mouse", item.getDescr() );
			em.remove( item );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
	}
