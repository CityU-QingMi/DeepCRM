	@Test
	public void testQueryScrolling() throws Throwable {
		prepare();
		Session s = getSessionUnderTest();
		Silly silly = new Silly( "silly" );
		s.save( silly );
		s.flush();

		ScrollableResults sr = s.createQuery( "from Silly" ).scroll();
		assertTrue( sr.next() );
		Silly silly2 = ( Silly ) sr.get( 0 );
		assertEquals( silly, silly2 );
		sr.close();

		sr = s.createQuery( "from Silly" ).scroll();
		ScrollableResults sr2 = s.createQuery( "from Silly where name = 'silly'" ).scroll();

		assertTrue( sr.next() );
		assertEquals( silly, sr.get( 0 ) );
		assertTrue( sr2.next() );
		assertEquals( silly, sr2.get( 0 ) );

		sr.close();
		sr2.close();

		s.delete( silly );
		s.flush();

		release( s );
		done();
	}
