    @Test
    public void testTupleAfterSubQuery() {
        EntityManager em = getOrCreateEntityManager();
        Query q = em.createQuery("SELECT e FROM EntityWithCompositeId e "
                + "WHERE EXISTS (SELECT 1 FROM EntityWithCompositeId) "
                + "AND e.id = :id");

        q.setParameter("id", new CompositeId(1, 2));

        assertThat(q.getResultList().size(), is(0));
    }
