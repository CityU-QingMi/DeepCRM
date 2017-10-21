    @Test
    @TestForIssue(jiraKey = "")
    public void loadIsMemeberQueriedCollection() {

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
        foo = em.find(Foo.class, foo.id);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bar> cq = cb.createQuery(Bar.class);
        Root<Bar> from = cq.from(Bar.class);

        Expression<Set<Foo>> foos = from.get("foos");

        cq.where(cb.isMember(foo, foos));

        TypedQuery<Bar> query = em.createQuery(cq);

        EntityGraph<Bar> barGraph = em.createEntityGraph( Bar.class );
        barGraph.addAttributeNodes("foos");
        query.setHint("javax.persistence.loadgraph", barGraph);

        Bar result = query.getSingleResult();

        assertTrue( Hibernate.isInitialized( result ) );
        assertTrue( Hibernate.isInitialized( result.foos ) );

        em.getTransaction().commit();
        em.close();
    }
