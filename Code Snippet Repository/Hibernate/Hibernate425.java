    @Test
    public void testFunctionWithJDBC() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-call-function-mysql-example[]
            final AtomicReference<Integer> phoneCount = new AtomicReference<>();
            Session session = entityManager.unwrap( Session.class );
            session.doWork( connection -> {
                try (CallableStatement function = connection.prepareCall(
                        "{ ? = call fn_count_phones(?) }" )) {
                    function.registerOutParameter( 1, Types.INTEGER );
                    function.setInt( 2, 1 );
                    function.execute();
                    phoneCount.set( function.getInt( 1 ) );
                }
            } );
            //end::sql-call-function-mysql-example[]
            assertEquals(Integer.valueOf(2), phoneCount.get());
        });
    }
