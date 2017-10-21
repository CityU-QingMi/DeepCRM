	@Test
	public void testUpdateQuery() {
		Item item = new Item( "Mouse", "Micro$oft mouse" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( item );
			assertTrue( em.contains( item ) );

			em.flush();
			em.clear();

			assertEquals(
					1, em.createNativeQuery(
							"update Item set descr = 'Logitech Mouse' where name = 'Mouse'"
					).executeUpdate()
			);
			item = em.find( Item.class, item.getName() );
			assertEquals( "Logitech Mouse", item.getDescr() );
			em.remove( item );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
