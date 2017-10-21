	@Test
	public void testIllegalArgumentExceptionBuildingSelectArrayWithSameAliases() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery();
		Root<Customer> customerRoot = criteria.from( Customer.class );
		Path<String> namePath = customerRoot.get( Customer_.name );
		Path<Integer> agePath = customerRoot.get( Customer_.age );
		try {
			CompoundSelection<Object[]> c = builder.array( namePath.alias( "SAME" ), agePath.alias( "SAME" ) );
			criteria.select( c );
			fail( "Attempt to define multi-select with same aliases should have thrown IllegalArgumentException" );
		}
		catch (IllegalArgumentException expected) {
		}
		em.getTransaction().commit();
		em.close();
	}
