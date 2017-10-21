    @Test
    @RequiresDialect( PostgreSQL81Dialect.class )
    public void HHH_10463_NullInCoalesce_PostgreSQL_Workaround() {
        doInHibernate( this::sessionFactory, session -> {
            Query query = session.createQuery("from Person p where p.name = coalesce(cast( :name as string) , p.name) ");
            query.setParameter("name", null);
            List<Person> resultList = query.getResultList();
            assertThat(resultList, hasItem(person));
        } );
    }
