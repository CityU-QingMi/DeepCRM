	@Test
	@TestForIssue(jiraKey = "")
	public void testMapKeyExpressionDereferenceInSelect() {
		doInHibernate( this::sessionFactory, s -> {
			List<String> keyValueNames = s.createQuery( "select key(v).name as name from TestEntity te join te.values v order by name", String.class ).list();
			assertEquals( 2, keyValueNames.size() );
			assertEquals( "key1", keyValueNames.get( 0 ) );
			assertEquals( "key2", keyValueNames.get( 1 ) );
		} );
	}
