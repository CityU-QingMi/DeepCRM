    @Test
    public void testNamedNativeQueryStoredProcedureRefCursorWithJDBC() {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Session session = entityManager.unwrap( Session.class );
            session.doWork( connection -> {
                try (CallableStatement function = connection.prepareCall(
                        "{ ? = call fn_person_and_phones( ? ) }" )) {
                    try {
                        function.registerOutParameter( 1, Types.REF_CURSOR );
                    }
                    catch ( SQLException e ) {
                        //OracleTypes.CURSOR
                        function.registerOutParameter( 1, -10 );
                    }
                    function.setInt( 2, 1 );
                    function.execute();
                    try (ResultSet resultSet = (ResultSet) function.getObject( 1);) {
                        while (resultSet.next()) {
                            Long postCommentId = resultSet.getLong(1);
                            String review = resultSet.getString(2);
                        }
                    }
                }
            } );
        }
        finally {
            entityManager.getTransaction().rollback();
            entityManager.close();
        }
    }
