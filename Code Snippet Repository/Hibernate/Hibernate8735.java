	@Test
	public void testBadClobDataSavedAsStringFails() {
		try {
			doInHibernate( this::sessionFactory, session -> {
							   final Query query = session.createQuery( "from TestEntity" );

							   final List<TestEntity> results = query.list();

							   fail("Exception thrown expected");
						   } );
		}
		catch (Exception e) {
			Exception rootException = (Exception) ExceptionUtil.rootCause( e );
			assertTrue( rootException.getMessage().startsWith( "Bad value for type long" ) );
		}
	}
