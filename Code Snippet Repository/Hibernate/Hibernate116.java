    @Test
    public void testFlushSQL() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            entityManager.createNativeQuery("delete from Person")
                .executeUpdate();
        });
        doInJPA( this::entityManagerFactory, entityManager -> {
            log.info("testFlushSQL");
            //tag::flushing-commit-flush-sql-example[]
            Person person = new Person("John Doe");
            entityManager.persist(person);

            assertTrue(((Number) entityManager
                .createNativeQuery("select count(*) from Person")
                .getSingleResult()).intValue() == 1);
            //end::flushing-commit-flush-sql-example[]
        });
    }
