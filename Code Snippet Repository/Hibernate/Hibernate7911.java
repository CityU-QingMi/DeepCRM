	@Test
	public void testJPAPositionalParameterList() {
		Session s = openSession();
		s.beginTransaction();
		ArrayList<String> params = new ArrayList<String>();
		params.add( "Doe" );
		params.add( "Public" );
		s.createQuery( "from Human where name.last in (?1)" )
				.setParameterList( "1", params )
				.list();

		s.createQuery( "from Human where name.last in ?1" )
				.setParameterList( "1", params )
				.list();

		s.createQuery( "from Human where nickName = ?1 and ( name.first = ?2 or name.last in (?3) )" )
				.setParameter( "1", "Yogster" )
				.setParameter( "2", "Yogi"  )
				.setParameterList( "3", params )
				.list();

		s.createQuery( "from Human where nickName = ?1 and ( name.first = ?2 or name.last in ?3 )" )
				.setParameter( "1", "Yogster" )
				.setParameter( "2", "Yogi" )
				.setParameterList( "3", params )
				.list();

		s.createQuery( "from Human where nickName = ?1 or ( name.first = ?2 and name.last in (?3) )" )
				.setParameter( "1", "Yogster" )
				.setParameter( "2", "Yogi"  )
				.setParameterList( "3", params )
				.list();

		s.createQuery( "from Human where nickName = ?1 or ( name.first = ?2 and name.last in ?3 )" )
				.setParameter( "1", "Yogster" )
				.setParameter( "2", "Yogi"  )
				.setParameterList( "3", params )
				.list();

		s.getTransaction().commit();
		s.close();
	}
