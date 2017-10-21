    @Test
   	public void loadInverseCollection() {
   		EntityManager em = getOrCreateEntityManager();
   		em.getTransaction().begin();

   		Bar bar = new Bar();
   		em.persist( bar );
   		Baz baz = new Baz();
   		em.persist( baz );

   		Foo foo = new Foo();
   		foo.bar = bar;
   		foo.baz = baz;
        bar.foos.add(foo);
        baz.foos.add(foo);
   		em.persist( foo );

   		em.getTransaction().commit();
   		em.clear();

   		em.getTransaction().begin();

   		EntityGraph<Foo> fooGraph = em.createEntityGraph( Foo.class );
   		fooGraph.addAttributeNodes("bar");
   		fooGraph.addAttributeNodes("baz");
        Subgraph<Bar> barGraph = fooGraph.addSubgraph("bar", Bar.class);
        barGraph.addAttributeNodes("foos");

   		Map<String, Object> properties = new HashMap<String, Object>();
   		properties.put( "javax.persistence.loadgraph", fooGraph );

   		Foo result = em.find( Foo.class, foo.id, properties );

   		assertTrue( Hibernate.isInitialized( result ) );
   		assertTrue( Hibernate.isInitialized( result.bar ) );
        assertTrue( Hibernate.isInitialized( result.bar.foos) );
   		assertTrue( Hibernate.isInitialized( result.baz ) );
   		// sanity check -- ensure the only bi-directional fetch was the one identified by the graph
        assertFalse( Hibernate.isInitialized( result.baz.foos) );

   		em.getTransaction().commit();
   		em.close();
   	}
