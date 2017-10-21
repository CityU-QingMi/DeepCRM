    @Test
    public void testNamedNativeQueryStoredProcedureRefCursor() {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        try {
            List<Object[]> postAndComments = entityManager
                    .createNamedQuery(
                            "fn_person_and_phones")
                    .setParameter(1, 1L)
                    .getResultList();
            Object[] postAndComment = postAndComments.get(0);
            Person person = (Person) postAndComment[0];
            Phone phone = (Phone) postAndComment[1];
            assertEquals(2, postAndComments.size());
        }
        finally {
            entityManager.getTransaction().rollback();
            entityManager.close();
        }
    }
