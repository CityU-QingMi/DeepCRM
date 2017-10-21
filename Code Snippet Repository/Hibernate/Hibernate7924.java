	@RequiresDialect(value = H2Dialect.class, jiraKey = "")
	public void testBooleanPredicate() {
		final Session session = openSession();

		session.getTransaction().begin();
		final Constructor constructor = new Constructor();
		session.save( constructor );
		session.getTransaction().commit();

		session.clear();
		Constructor.resetConstructorExecutionCount();

		session.getTransaction().begin();
		final Constructor result = (Constructor) session.createQuery(
				"select new Constructor( c.id, c.id is not null, c.id = c.id, c.id + 1, concat( c.id, 'foo' ) ) from Constructor c where c.id = :id"
		).setParameter( "id", constructor.getId() ).uniqueResult();
		session.getTransaction().commit();

		assertEquals( 1, Constructor.getConstructorExecutionCount() );
		assertEquals( new Constructor( constructor.getId(), true, true, constructor.getId() + 1, constructor.getId() + "foo" ), result );

		session.close();
	}
