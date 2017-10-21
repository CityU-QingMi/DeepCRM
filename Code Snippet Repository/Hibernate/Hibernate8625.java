	@Test
	public void testQueries() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Long id = ( Long ) s.save( new TrivialClass() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		TrivialClass tc = (TrivialClass) s.load(TrivialClass.class, id);
		s.createQuery( "from TrivialClass s where s.id = 2" ).list();
		s.createQuery( "select t.count from Top t" ).list();
		s.createQuery( "from Lower s where s.another.name='name'" ).list();
		s.createQuery( "from Lower s where s.yetanother.name='name'" ).list();
		s.createQuery( "from Lower s where s.yetanother.name='name' and s.yetanother.foo is null" ).list();
		s.createQuery( "from Top s where s.count=1" ).list();
		s.createQuery( "select s.count from Top s, Lower ls where ls.another=s" ).list();
		s.createQuery( "select elements(ls.bag), elements(ls.set) from Lower ls" ).list();
		s.createQuery( "from Lower" ).iterate();
		s.createQuery( "from Top" ).iterate();
		s.delete(tc);
		s.getTransaction().commit();
		s.close();
	}
