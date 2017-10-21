    @Test
    public void testHibernateProcedureCallReturnValueParameter() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-hibernate-call-sp-no-out-mysql-example[]
            Session session = entityManager.unwrap( Session.class );

            ProcedureCall call = session.createStoredProcedureCall( "sp_phones" );
            call.registerParameter( 1, Long.class, ParameterMode.IN ).bindValue( 1L );

            Output output = call.getOutputs().getCurrent();

            List<Object[]> personComments = ( (ResultSetOutput) output ).getResultList();
            //end::sql-hibernate-call-sp-no-out-mysql-example[]
            assertEquals( 2, personComments.size() );
        });
    }
