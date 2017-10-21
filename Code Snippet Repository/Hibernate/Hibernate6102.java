	@Test
	public void testEscapeCharacter() throws Exception {
		final Item item = new Item( "Mouse", "Micro_oft mouse" );
		final Item item2 = new Item( "Computer", "Dell computer" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( item );
			em.persist( item2 );
			assertTrue( em.contains( item ) );
			em.getTransaction().commit();

			em.getTransaction().begin();
			Query q = em.createQuery( "select item from Item item where item.descr like 'Microk_oft mouse' escape 'k' " );
			List result = q.getResultList();
			assertNotNull( result );
			assertEquals( 1, result.size() );
			int deleted = em.createQuery( "delete from Item" ).executeUpdate();
			assertEquals( 2, deleted );
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
