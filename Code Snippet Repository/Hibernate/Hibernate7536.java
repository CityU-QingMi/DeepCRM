	@Test
	public void testDetachedCriteriaTimeout() {
		doInHibernate( this::sessionFactory, session -> {
			DetachedCriteria dc = DetachedCriteria.forClass(Student.class)
					.setTimeout( 100 );

			Student gavin = new Student();
			gavin.setName("Gavin King");
			gavin.setStudentNumber(232);

			session.persist(gavin);
			session.flush();

			connectionProvider.clear();
			List result = dc.getExecutableCriteria(session).list();
			PreparedStatement preparedStatement = connectionProvider.getPreparedStatements().get( 0 );
			try {
				verify(preparedStatement, times(1)).setQueryTimeout( 100 );
			}
			catch (SQLException e) {
				fail(e.getMessage());
			}

			assertEquals( result.size(), 1 );

			session.delete(gavin);
		} );
	}
