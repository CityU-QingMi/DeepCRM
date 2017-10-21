    @Test
    public void testFlushJPQL() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            log.info("testFlushJPQL");
            //tag::flushing-commit-flush-jpql-example[]
            Person person = new Person("John Doe");
            entityManager.persist(person);

            entityManager.createQuery("select p from Advertisement p")
                .setFlushMode( FlushModeType.COMMIT)
                .getResultList();

            entityManager.createQuery("select p from Person p")
                .setFlushMode( FlushModeType.COMMIT)
                .getResultList();
            //end::flushing-commit-flush-jpql-example[]
        });
    }
