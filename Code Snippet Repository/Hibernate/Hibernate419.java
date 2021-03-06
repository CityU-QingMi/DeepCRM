    @After
    public void destroy() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            Session session = entityManager.unwrap( Session.class );
            session.doWork( connection -> {
                try(Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DROP PROCEDURE IF EXISTS sp_count_phones");
                }
                catch (SQLException ignore) {
                }
            } );
        });
        doInJPA( this::entityManagerFactory, entityManager -> {
            Session session = entityManager.unwrap( Session.class );
            session.doWork( connection -> {
                try(Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DROP PROCEDURE IF EXISTS sp_phones");
                }
                catch (SQLException ignore) {
                }
            } );
        });
        doInJPA( this::entityManagerFactory, entityManager -> {
            Session session = entityManager.unwrap( Session.class );
            session.doWork( connection -> {
                try(Statement statement = connection.createStatement()) {
                    statement.executeUpdate("DROP FUNCTION IF EXISTS fn_count_phones");
                }
                catch (SQLException ignore) {
                }
            } );
        });
    }
