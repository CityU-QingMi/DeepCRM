	@Test
	public void testJoins() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from Lower l join l.yetanother l2 where lower(l2.name) > 'a'" ).list();
		s.createQuery( "from Lower l where lower(l.yetanother.top.name) > 'a'" ).list();
		s.createQuery( "from SubMulti sm join sm.children smc where smc.name > 'a'" ).list();
		s.createQuery( "select s, ya from Lower s join s.yetanother ya" ).list();
		s.createQuery( "from Lower s1 join s1.bag s2" ).list();
		s.createQuery( "from Lower s1 left join s1.bag s2" ).list();
		s.createQuery( "select s, a from Lower s join s.another a" ).list();
		s.createQuery( "select s, a from Lower s left join s.another a" ).list();
		s.createQuery( "from Top s, Lower ls" ).list();
		s.createQuery( "from Lower ls join ls.set s where s.name > 'a'" ).list();
		s.createQuery( "from Po po join po.list sm where sm.name > 'a'" ).list();
		s.createQuery( "from Lower ls inner join ls.another s where s.name is not null" ).list();
		s.createQuery( "from Lower ls where ls.other.another.name is not null" ).list();
		s.createQuery( "from Multi m where m.derived like 'F%'" ).list();
		s.createQuery( "from SubMulti m where m.derived like 'F%'" ).list();
		s.getTransaction().commit();
		s.close();
	}
