	@Test
	public void testManyToOneExplicitJoinColumn() throws Exception {
		assertTrue( SchemaUtil.isColumnPresent( "FinancialHistory", "FK", metadata() ) );
		assertTrue( ! SchemaUtil.isColumnPresent( "FinancialHistory", "id", metadata() ) );
		Person e = new Person();
		e.ssn = "aaa";
		Session s = openSession(  );
		s.getTransaction().begin();
		s.persist( e );
		FinancialHistory d = new FinancialHistory();
		d.patient = e;
		//d.id = "aaa"; //FIXME not needed when foreign is enabled
		s.persist( d );
		s.flush();
		s.clear();
		d = (FinancialHistory) s.get( FinancialHistory.class, d.id );
		assertEquals( d.id, d.patient.ssn );
		d.lastupdate = new Date();
		s.flush();
		s.clear();
		d = (FinancialHistory) s.get( FinancialHistory.class, d.id );
		assertNotNull( d.lastupdate );
		s.getTransaction().rollback();
		s.close();
	}
