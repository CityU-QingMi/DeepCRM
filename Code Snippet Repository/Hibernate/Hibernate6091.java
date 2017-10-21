	@Test
	@TestForIssue(jiraKey = "")
	public void setParameterWithWrongTypeShouldNotThrowIllegalArgumentExceptionWhenValidationIsDisabled() {
		final SessionFactory sessionFactory = entityManagerFactory().unwrap( SessionFactory.class );
		final Session session = sessionFactory.withOptions().setQueryParameterValidation( false ).openSession();
		try {
			session.createQuery( "select e from TestEntity e where e.id = :id" ).setParameter( "id", 1 );
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
