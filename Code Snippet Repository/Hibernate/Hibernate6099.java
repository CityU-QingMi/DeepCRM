	@Test
	@TestForIssue(jiraKey = "")
	public void testMultipleParameterLists() throws Exception {
		final Item item = new Item( "Mouse", "Micro$oft mouse" );
		final Item item2 = new Item( "Computer", "Dell computer" );

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( item );
			em.persist( item2 );
			assertTrue( em.contains( item ) );
			em.getTransaction().commit();


			List<String> names = Arrays.asList( item.getName() );
			Query q = em.createQuery( "select item from Item item where item.name in :names or item.name in :names2" );
			q.setParameter( "names", names );
			q.setParameter( "names2", names );
			List result = q.getResultList();
			assertNotNull( result );
			assertEquals( 1, result.size() );

			List<String> descrs = Arrays.asList( item.getDescr() );
			q = em.createQuery(
					"select item from Item item where item.name in :names and ( item.descr is null or item.descr in :descrs )" );
			q.setParameter( "names", names );
			q.setParameter( "descrs", descrs );
			result = q.getResultList();
			assertNotNull( result );
			assertEquals( 1, result.size() );

			em.getTransaction().begin();
			em.remove( em.getReference( Item.class, item.getName() ) );
			em.remove( em.getReference( Item.class, item2.getName() ) );
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
