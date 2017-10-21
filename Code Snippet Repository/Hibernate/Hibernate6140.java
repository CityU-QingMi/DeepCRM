	@Test
	public void testGetAliasReturnNullIfNoAliasExist() {
		EntityManager em = getOrCreateEntityManager();
		try {
			em.getTransaction().begin();
			User u = new User( "Fab" );
			em.persist( u );
			em.getTransaction().commit();

			TypedQuery<Tuple> query = em.createQuery( "SELECT u.firstName from User u", Tuple.class );

			List<Tuple> result = query.getResultList();
			List<TupleElement<?>> elements = result.get( 0 ).getElements();

			assertThat( elements.size(), is( 1 ) );
			final String alias = elements.get( 0 ).getAlias();
			assertThat( alias, is( nullValue() ) );
		}
		catch (Exception e) {
			if ( em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
	}
