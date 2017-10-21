    @Test
    @SkipForDialect( jiraKey = "", value =  PostgreSQL81Dialect.class)
    @SkipForDialect( jiraKey = "", value =  Oracle8iDialect.class)
    public void HHH_10463_NullInCoalesce() {
        doInHibernate( this::sessionFactory, session -> {
            Query query = session.createQuery("from Person p where p.name = coalesce(:name, p.name) ");
            query.setParameter("name", null);
            List<Person> resultList = query.getResultList();
            assertThat(resultList, hasItem(person));
        } );
    }
