    @Test
    public void testHibernateProcedureCallRefCursor() {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Session session = entityManager.unwrap(Session.class);

            ProcedureCall call = session.createStoredProcedureCall( "sp_person_phones");
            call.registerParameter(1, Long.class, ParameterMode.IN).bindValue(1L);
            call.registerParameter(2, Class.class, ParameterMode.REF_CURSOR);

            Output output = call.getOutputs().getCurrent();
            List<Object[]> postComments = ( (ResultSetOutput) output ).getResultList();
            assertEquals(2, postComments.size());
        }
        finally {
            entityManager.getTransaction().rollback();
            entityManager.close();
        }
    }
