	@Test
	public void testDiscriminator() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Dictionary dic = new Dictionary();
		dic.setName( "Anglais-Francais" );
		dic.setEditor( "Harrap's" );
		SynonymousDictionary syn = new SynonymousDictionary();
		syn.setName( "Synonymes de tous les temps" );
		syn.setEditor( "Imagination edition" );
		s.persist( dic );
		s.persist( syn );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		List results = s.getNamedQuery( "all.dictionaries" ).list();
		assertEquals( 2, results.size() );
		assertTrue(
				results.get( 0 ) instanceof SynonymousDictionary
						|| results.get( 1 ) instanceof SynonymousDictionary
		);
		s.delete( results.get( 0 ) );
		s.delete( results.get( 1 ) );
		tx.commit();
		s.close();
	}
