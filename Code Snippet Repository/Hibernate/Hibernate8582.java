	@Test
	public void testBeanResultTransformer() throws HibernateException, SQLException {
		Session s = openSession();
		Transaction transaction = s.beginTransaction();
		Fum fum = new Fum( fumKey("fum") );
		fum.setFo( new Fum( fumKey("fo") ) );
		fum.setFum("fo fee fi");
		fum.getFo().setFum("stuff");
		Fum fr = new Fum( fumKey("fr") );
		fr.setFum("goo");
		Fum fr2 = new Fum( fumKey("fr2") );
		fr2.setFum("soo");
		fum.setFriends( new HashSet() );
		fum.getFriends().add(fr);
		fum.getFriends().add(fr2);
		s.save(fr);
		s.save(fr2);
		s.save( fum.getFo() );
		s.save(fum);
		
		Criteria test = s.createCriteria(Fum.class, "xam")
			.createCriteria("fo", "fo")
			.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		Map fc = (Map) test.list().get(0);
		assertNotNull(fc.get("xam"));
		
		Criteria base = s.createCriteria(Fum.class, "fum")
		.add( Restrictions.like("fum", "f%") )
		.setResultTransformer(Transformers.aliasToBean(ABean.class))
		.setFetchMode("friends", FetchMode.JOIN);
		base.createCriteria("fo", "fo")
		.add( Restrictions.eq( "fum", fum.getFo().getFum() ) );
		ABean map = (ABean) base.list().get(0);

		assertTrue(
				map.getFum()==fum &&
				map.getFo()==fum.getFo() );
		
		s.delete(fr);
		s.delete(fr2);
		s.delete(fum);
		s.delete(fum.getFo());
		s.flush();
		transaction.commit();
		s.close();
	}
