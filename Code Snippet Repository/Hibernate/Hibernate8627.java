	@Test
	public void testMultiTableManyToOne() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		assertTrue( s.createQuery( "from Top" ).list().size() == 0 );
		Multi multi = new Multi();
		multi.setExtraProp( "extra" );
		multi.setName("name");
		s.save(multi);
		Lower ls = new Lower();
		ls.setOther(ls);
		ls.setAnother(multi);
		ls.setYetanother(ls);
		ls.setName("Less Simple");
		Serializable id = s.save(ls);
		t.commit();
		s.close();
		assertTrue( ls.getOther()==ls && ls.getAnother()==multi && ls.getYetanother()==ls );

		s = openSession();
		t = s.beginTransaction();
		try {
			// MySQL does not like deleting rows that refer to itself without first
			// null'ing out the FK.  Ugh...
			ls = s.load( Lower.class, id );
			assertTrue( ls.getOther() == ls && ls.getYetanother() == ls );
			assertTrue( ls.getAnother().getName().equals( "name" ) && ls.getAnother() instanceof Multi );
			s.delete( ls.getAnother() );
			ls.setOther( null );
			ls.setAnother( null );
			ls.setYetanother( null );
			ls.getSet().clear();
			s.flush();
			s.delete( ls );
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			throw e;
		}
		finally {
			s.close();
		}
	}
