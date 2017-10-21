	@Test
	public void testMapKeyExpressionInWhere() {
		doInHibernate( this::sessionFactory, s -> {
			// JPA form
			Query query = s.createQuery( "select te from TestEntity te join te.values v where ? in (key(v)) " );
			query.setParameter( 0, keyValue );

			assertThat( query.list().size(), is( 1 ) );

			// Hibernate additional form
			query = s.createQuery( "select te from TestEntity te where ? in (key(te.values))" );
			query.setParameter( 0, keyValue );

			assertThat( query.list().size(), is( 1 ) );

			// Test key property dereference
			query = s.createQuery( "select te from TestEntity te join te.values v where key(v).name in :names" );
			query.setParameterList( "names", Arrays.asList( keyValue.name ) );
			assertThat( query.list().size(), is( 1 ) );
		} );
	}
