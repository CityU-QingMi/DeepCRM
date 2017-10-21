    @Test
    @TestForIssue(jiraKey = "")
    public void collectionAttributeSubgraphInheritanceTest() {
        EntityManager em = getOrCreateEntityManager();
        em.getTransaction().begin();

        Bar bar = new Bar();
        em.persist(bar);

        Foo foo = new Foo();
        foo.bar = bar;
        em.persist( foo );

        Foo2 foo2 = new Foo2();
        foo2.foo = foo;
        em.persist( foo2 );

        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();

        EntityGraph<Foo2> entityGraph = em.createEntityGraph( Foo2.class );
        Subgraph<Foo> subgraphFoo = entityGraph.addSubgraph( "foo" );
        subgraphFoo.addSubgraph( "bars" );

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put( "javax.persistence.loadgraph", entityGraph );

        Foo2 result = em.find( Foo2.class, foo2.id, properties );

        assertTrue( Hibernate.isInitialized( result ) );
        assertTrue( Hibernate.isInitialized( result.foo ) );
        assertTrue( Hibernate.isInitialized( result.foo.bars ) );

        em.getTransaction().commit();
        em.close();
    }
