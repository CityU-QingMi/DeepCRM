	@Test
    @SkipForDialect( value = SybaseASE15Dialect.class, jiraKey = "")
	public void testRegisteredFunction() {
		Session s = openSession();
		s.getTransaction().begin();
		Entity e = new Entity( "name " );
		s.save( e );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		e = ( Entity ) s.get( Entity.class, e.getId() );
		assertFalse( e.getName().startsWith( StoredPrefixedStringType.PREFIX ) );
		assertEquals( "name ", e.getName() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		s.delete( e );
		s.getTransaction().commit();
		s.close();
	}
