	private void prepareTestData() {
		char open = getDialect().openQuote();
		char close = getDialect().closeQuote();
		queryString="select t."+open+"NAME"+close+" as "+open+"QuotEd_nAMe"+close+" from "+open+"MY_ENTITY_TABLE"+close+" t";
		Session s = sessionFactory().openSession();
		s.beginTransaction();
		s.save( new MyEntity( "mine" ) );
		s.getTransaction().commit();
		s.close();
	}
