	@Test
	@TestForIssue(jiraKey = "")
	public void testMapValueExpressionInWhere() {
		doInHibernate( this::sessionFactory, s -> {
			// JPA form
			try {
				Query query = s.createQuery( "select te from TestEntity te join te.values v where ? in (value(v))" );
				query.setParameter( 0, new EmbeddableValue( 3 ) );
				assertThat( query.list().size(), is( 2 ) );
				fail( "HibernateException expected - Could not determine type for EmbeddableValue" );
			}
			catch ( Exception e ) {
				assertTyping( HibernateException.class, e );
			}

			// Hibernate additional form
			try {
				Query query = s.createQuery( "select te from TestEntity te where ? in (value(te.values))" );
				query.setParameter( 0, new EmbeddableValue( 3 ) );
				assertThat( query.list().size(), is( 2 ) );
				fail( "HibernateException expected - Could not determine type for EmbeddableValue" );
			}
			catch ( Exception e ) {
				assertTyping( HibernateException.class, e );
			}

			// Test value property dereference
			Query query = s.createQuery( "select te from TestEntity te join te.values v where value(v).value in :values" );
			query.setParameterList( "values", Arrays.asList( 3 ) );
			assertThat( query.list().size(), is( 2 ) );
		} );
	}
