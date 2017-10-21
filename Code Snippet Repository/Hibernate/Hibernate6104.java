	@Test
	public void testNativeQueryByResultSet() {
		Item item = new Item( "Mouse", "Micro$oft mouse" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( item );
			assertTrue( em.contains( item ) );
			em.getTransaction().commit();

			em.getTransaction().begin();
			item = (Item) em.createNativeQuery(
					"select name as itemname, descr as itemdescription from Item",
					"getItem"
			)
					.getSingleResult();
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
