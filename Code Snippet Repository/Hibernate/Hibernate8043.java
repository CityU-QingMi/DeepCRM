    @Test
    public void HHH_10463_TestCoalesce() {
        doInHibernate( this::sessionFactory, session -> {
            Query query = session.createQuery( "from Person p where p.name = coalesce(:name , p.name) ");
            query.setParameter("name", "Johannes");
            List<Person> resultList = query.getResultList();
            assertThat(resultList, hasItem(person));
        } );

    }
