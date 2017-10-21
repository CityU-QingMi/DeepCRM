	public void testPrepareStatementFaultIntercept() {
		final Interceptor interceptor = new EmptyInterceptor() {
			@Override
			public String onPrepareStatement(String sql) {
				return null;
			}
		};

		Session s = openSession( interceptor );
		try {

			Transaction t = s.beginTransaction();
			User u = new User( "Kinga", "Mroz" );
			s.persist( u );
			t.commit();
		}catch (TransactionException e){
			assertTrue( e.getCause() instanceof AssertionFailure );
		}finally {
			s.close();
		}
	}
