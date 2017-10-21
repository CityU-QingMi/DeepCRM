    @Test
    public void testFlushSQL() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            entityManager.createNativeQuery("delete from Person").executeUpdate();
        });
        doInJPA( this::entityManagerFactory, entityManager -> {
            log.info("testFlushSQL");
            //tag::flushing-manual-flush-example[]
            Person person = new Person("John Doe");
            entityManager.persist(person);

            Session session = entityManager.unwrap( Session.class);
            session.setHibernateFlushMode( FlushMode.MANUAL );

            assertTrue(((Number) entityManager
                .createQuery("select count(id) from Person")
                .getSingleResult()).intValue() == 0);

            assertTrue(((Number) session
                .createNativeQuery("select count(*) from Person")
                .uniqueResult()).intValue() == 0);
            //end::flushing-manual-flush-example[]
        });
    }
