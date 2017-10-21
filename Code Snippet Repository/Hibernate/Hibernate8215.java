    @Test
    @TestForIssue(jiraKey = "")
    @RequiresDialect(value=SQLServer2012Dialect.class)
    public void testStartOfSequence() {
		final Person person = doInHibernate( this::sessionFactory, session -> {
			final Person _person = new Person();
			session.persist(_person);
			return _person;
		} );

        assertTrue(person.getId() == 10);
	}
