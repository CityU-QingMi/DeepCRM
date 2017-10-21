	@Test
	@TestForIssue(jiraKey = "")
	public void loadMultipleAssociations() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		Bar bar = new Bar();
		em.persist( bar );

		Baz baz = new Baz();
		em.persist( baz );

		Foo foo = new Foo();
		foo.bar = bar;
		foo.baz = baz;
		em.persist( foo );

		em.getTransaction().commit();
		em.clear();

		em.getTransaction().begin();

		EntityGraph<Foo> fooGraph = em.createEntityGraph( Foo.class );
		fooGraph.addAttributeNodes( "bar", "baz" );

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put( "javax.persistence.loadgraph", fooGraph );

		Foo result = em.find( Foo.class, foo.id, properties );

		assertTrue( Hibernate.isInitialized( result ) );
		assertTrue( Hibernate.isInitialized( result.bar ) );
		assertTrue( Hibernate.isInitialized( result.baz ) );

		em.getTransaction().commit();
		em.close();
	}
