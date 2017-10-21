	@Test
	public void testVariousTupleAccessMethods() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Customer c1 = new Customer();
		c1.setId( "c1" );
		c1.setAge( 18 );
		c1.setName( "Bob" );
		em.persist( c1 );
		em.getTransaction().commit();
		em.close();

		em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
		Root<Customer> customerRoot = criteria.from( Customer.class );
		Path<String> namePath = customerRoot.get( Customer_.name );
		namePath.alias( "NAME" );
		Path<Integer> agePath = customerRoot.get( Customer_.age );
		agePath.alias( "AGE" );
		criteria.multiselect( namePath, agePath );

		List<Tuple> results = em.createQuery( criteria ).getResultList();
		Tuple tuple = results.get( 0 );
		assertNotNull( tuple );
		assertNotNull( tuple.get( "NAME" ) );
		assertNotNull( tuple.get( "NAME", String.class ) );
		try {
			tuple.get( "NAME", Date.class );
			fail( "Accessing Customer#name tuple as Date should have thrown exception" );
		}
		catch (IllegalArgumentException expected) {
		}

		em.getTransaction().commit();
		em.close();

		em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete Customer" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
