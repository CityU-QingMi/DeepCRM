    @Test
    public void testHibernateProcedureCallRefCursor() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-hibernate-call-sp-ref-cursor-oracle-example[]
            Session session = entityManager.unwrap(Session.class);
            
            ProcedureCall call = session.createStoredProcedureCall( "sp_person_phones");
            call.registerParameter(1, Long.class, ParameterMode.IN).bindValue(1L);
            call.registerParameter(2, Class.class, ParameterMode.REF_CURSOR);

            Output output = call.getOutputs().getCurrent();
            List<Object[]> postComments = ( (ResultSetOutput) output ).getResultList();
            assertEquals(2, postComments.size());
            //end::sql-hibernate-call-sp-ref-cursor-oracle-example[]
        });
    }
