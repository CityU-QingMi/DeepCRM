	@Test
	public void testCompositeId() throws Exception {
		Session s;
		Transaction tx;
		RegionalArticlePk pk = new RegionalArticlePk();
		pk.iso2 = "FR";
		pk.localUniqueKey = "1234567890123";
		RegionalArticle reg = new RegionalArticle();
		reg.setName( "Je ne veux pes rester sage - Dolly" );
		reg.setPk( pk );
		s = openSession();
		tx = s.beginTransaction();
		s.persist( reg );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		reg = (RegionalArticle) s.get( RegionalArticle.class, reg.getPk() );
		assertNotNull( reg );
		assertNotNull( reg.getPk() );
		assertEquals( "Je ne veux pes rester sage - Dolly", reg.getName() );
		assertEquals( "FR", reg.getPk().iso2 );
		tx.commit();
		s.close();
	}
