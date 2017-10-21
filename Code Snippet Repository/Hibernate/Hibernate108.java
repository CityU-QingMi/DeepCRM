    @Test
    public void testFlushSQL() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            entityManager.createNativeQuery("delete from Person").executeUpdate();
        });
        doInJPA( this::entityManagerFactory, entityManager -> {
            log.info("testFlushSQL");
            //tag::flushing-always-flush-sql-example[]
            Person person = new Person("John Doe");
            entityManager.persist(person);

            Session session = entityManager.unwrap( Session.class);
            assertTrue(((Number) session
                    .createNativeQuery("select count(*) from Person")
                    .setFlushMode( FlushMode.ALWAYS)
                    .uniqueResult()).intValue() == 1);
            //end::flushing-always-flush-sql-example[]
        });
    }
