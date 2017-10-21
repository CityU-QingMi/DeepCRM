	@Test
	public void testWithoutIntegrator() {
		SessionFactory sf = new Configuration().addAnnotatedClass( Investor.class )
				.setProperty( "hibernate.hbm2ddl.auto", "create-drop" )
				.buildSessionFactory();

		try {
			Session sess = sf.openSession();
			try {
				sess.getTransaction().begin();
				Investor myInv = getInvestor();
				myInv.setId( 1L );

				sess.save( myInv );
				sess.flush();
				fail("A JDBCException expected");

				sess.clear();

				Investor inv = (Investor) sess.get( Investor.class, 1L );
				assertEquals( new BigDecimal( "100" ), inv.getInvestments().get( 0 ).getAmount().getAmount() );
			}catch (PersistenceException e){
				assertTyping(JDBCException.class, e.getCause());
				sess.getTransaction().rollback();
			}
			sess.close();
		}
		finally {
			sf.close();
		}
	}
