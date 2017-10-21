	@Test
	public void testIllegalArgumentExceptionBuildingTupleWithSameAliases() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
		Root<Customer> customerRoot = criteria.from( Customer.class );
		Path<String> namePath = customerRoot.get( Customer_.name );
		namePath.alias( "age" );
		Path<Integer> agePath = customerRoot.get( Customer_.age );
		agePath.alias( "age" );
		try {
			criteria.multiselect( namePath, agePath );
			fail( "Attempt to define multi-select with same aliases should have thrown IllegalArgumentException" );
		}
		catch (IllegalArgumentException expected) {
		}
		em.getTransaction().commit();
		em.close();
	}
