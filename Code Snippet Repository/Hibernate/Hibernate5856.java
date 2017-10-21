    @Test
    @TestForIssue(jiraKey = "")
    public void attributeNodeInheritanceTest() {
        EntityManager em = getOrCreateEntityManager();
        em.getTransaction().begin();

        Manager manager = new Manager();
        em.persist( manager );
        Employee employee = new Employee();
        manager.friends.add( employee);
        em.persist( employee );
        Manager anotherManager = new Manager();
        manager.managers.add(anotherManager);
        em.persist( anotherManager );
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();

        EntityGraph<Manager> entityGraph = em.createEntityGraph( Manager.class );
        entityGraph.addAttributeNodes( "friends" );
        entityGraph.addAttributeNodes( "managers" );

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put( "javax.persistence.loadgraph", entityGraph );

        Manager result = em.find( Manager.class, manager.id, properties );

        assertTrue( Hibernate.isInitialized( result ) );
        assertTrue( Hibernate.isInitialized( result.friends ) );
        assertEquals( result.friends.size(), 1 );
        assertTrue( Hibernate.isInitialized( result.managers) );
        assertEquals( result.managers.size(), 1 );

        em.getTransaction().commit();
        em.close();
    }
