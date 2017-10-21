	@Test
	public void testComponentQueries() {
		Session s = openSession();
		s.beginTransaction();

		Type[] types = s.createQuery( "select h.name from Human h" ).getReturnTypes();
		assertEquals( 1, types.length );
		assertTrue( types[0] instanceof ComponentType );

		// Test the ability to perform comparisons between component values
		s.createQuery( "from Human h where h.name = h.name" ).list();
		s.createQuery( "from Human h where h.name = :name" ).setParameter( "name", new Name() ).list();
		s.createQuery( "from Human where name = :name" ).setParameter( "name", new Name() ).list();
		s.createQuery( "from Human h where :name = h.name" ).setParameter( "name", new Name() ).list();
		s.createQuery( "from Human h where :name <> h.name" ).setParameter( "name", new Name() ).list();

		// Test the ability to perform comparisons between a component and an explicit row-value
		s.createQuery( "from Human h where h.name = ('John', 'X', 'Doe')" ).list();
		s.createQuery( "from Human h where ('John', 'X', 'Doe') = h.name" ).list();
		s.createQuery( "from Human h where ('John', 'X', 'Doe') <> h.name" ).list();

		s.createQuery( "from Human h order by h.name" ).list();

		s.getTransaction().commit();
		s.close();
	}
