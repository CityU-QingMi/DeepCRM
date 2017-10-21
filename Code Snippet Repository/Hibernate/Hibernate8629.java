	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testCollection() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Multi multi1 = new Multi();
		multi1.setExtraProp("extra1");
		Multi multi2 = new Multi();
		multi2.setExtraProp("extra2");
		Po po = new Po();
		multi1.setPo(po); multi2.setPo(po);
		po.setSet( new HashSet() );
		po.getSet().add(multi1);
		po.getSet().add(multi2);
		po.setList( new ArrayList() );
		//po.getList().add(null);
		po.getList().add( new SubMulti() );
		Serializable id = s.save(po);
		assertTrue( id!=null );
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		po = (Po) s.load(Po.class, id);
		assertTrue( po.getSet().size()==2 );
		assertTrue( po.getList().size()==1 );
		s.delete(po);
		assertTrue( s.createQuery( "from Top" ).list().size()==0 );
		t.commit();
		s.close();
	}
