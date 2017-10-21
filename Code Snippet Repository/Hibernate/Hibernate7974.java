	@Test
	public void testIllegalMixedTransformerQueries() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		try {
			getSelectNewQuery( session ).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			fail("'select new' together with a resulttransformer should result in error!");
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch(QueryException he) {
			assertTrue(he.getMessage().indexOf("ResultTransformer")==0);
		}

		try {
			getSelectNewQuery( session ).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).iterate();
			fail("'select new' together with a resulttransformer should result in error!");
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch(HibernateException he) {
			assertTrue(he.getMessage().indexOf("ResultTransformer")==0);
		}

		try {
			getSelectNewQuery( session ).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).scroll();
			fail("'select new' together with a resulttransformer should result in error!");
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch(HibernateException he) {
			assertTrue(he.getMessage().indexOf("ResultTransformer")==0);
		}
		t.commit();
		session.close();
	}
