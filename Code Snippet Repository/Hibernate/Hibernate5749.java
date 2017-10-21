    @After
    public void cleanupTestData() {
        EntityManager em = getOrCreateEntityManager();
        em.getTransaction().begin();
        em.remove( em.find( Thing.class, "thing1" ) );
        em.remove( em.find( Thing.class, "thing2" ) );
        em.remove( em.find( ThingWithQuantity.class, "thingWithQuantity3" ) );
        em.getTransaction().commit();
        em.close();
    }
