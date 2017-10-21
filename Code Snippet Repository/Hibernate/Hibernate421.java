    @Test
    public void testHibernateProcedureCallOutParameter() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-hibernate-call-sp-out-mysql-example[]
            Session session = entityManager.unwrap( Session.class );

            ProcedureCall call = session.createStoredProcedureCall( "sp_count_phones" );
            call.registerParameter( "personId", Long.class, ParameterMode.IN ).bindValue( 1L );
            call.registerParameter( "phoneCount", Long.class, ParameterMode.OUT );

            Long phoneCount = (Long) call.getOutputs().getOutputParameterValue( "phoneCount" );
            assertEquals( Long.valueOf( 2 ), phoneCount );
            //end::sql-hibernate-call-sp-out-mysql-example[]
        });
    }
