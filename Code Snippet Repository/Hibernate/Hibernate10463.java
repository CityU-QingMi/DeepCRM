	@Test
	@Priority(10)
	public void initData() {
		Session session = openSession();

		// Rev 1
		session.getTransaction().begin();
		Person p = new Person();
		Name n = new Name();
		n.setName( "name1" );
		p.getNames().add( n );
		session.saveOrUpdate( p );
		session.getTransaction().commit();

		// Rev 2
		session.getTransaction().begin();
		n.setName( "Changed name" );
		session.saveOrUpdate( p );
		session.getTransaction().commit();

		// Rev 3
		session.getTransaction().begin();
		Name n2 = new Name();
		n2.setName( "name2" );
		p.getNames().add( n2 );
		session.getTransaction().commit();

		personId = p.getId();

		session.close();
	}
