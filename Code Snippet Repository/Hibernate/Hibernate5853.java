    @Test
   	public void loadCollection() {
   		EntityManager em = getOrCreateEntityManager();
   		em.getTransaction().begin();

   		Bar bar = new Bar();
   		em.persist( bar );

   		Foo foo = new Foo();
   		foo.bar = bar;
        bar.foos.add(foo);
   		em.persist( foo );

   		em.getTransaction().commit();
   		em.clear();

   		em.getTransaction().begin();

   		EntityGraph<Bar> barGraph = em.createEntityGraph( Bar.class );
   		barGraph.addAttributeNodes("foos");

   		Map<String, Object> properties = new HashMap<String, Object>();
   		properties.put( "javax.persistence.loadgraph", barGraph);

   		Bar result = em.find( Bar.class, bar.id, properties );

   		assertTrue( Hibernate.isInitialized( result ) );
   		assertTrue( Hibernate.isInitialized( result.foos ) );

   		em.getTransaction().commit();
   		em.close();
   	}
