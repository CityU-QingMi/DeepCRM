	@Test
	public void testOuterJoin() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Eye e = new Eye();
		e.setName("Eye Eye");
		Jay jay = new Jay(e);
		e.setJay( jay );
		s.saveOrUpdate( e );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		e = (Eye) s.createCriteria(Eye.class).uniqueResult();
		assertTrue( Hibernate.isInitialized( e.getJay() ) );
		assertTrue( Hibernate.isInitialized( e.getJays() ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		jay = (Jay) s.createQuery("select new Jay(eye) from Eye eye").uniqueResult();
		assertTrue( "Eye Eye".equals( jay.getEye().getName() ) );
		s.delete( jay.getEye() );
		s.getTransaction().commit();
		s.close();
	}
