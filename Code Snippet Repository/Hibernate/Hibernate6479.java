	@Test
	public void testWithTypeContributor() {
		SessionFactory sf = new Configuration().addAnnotatedClass( Investor.class )
				.registerTypeContributor( new InvestorTypeContributor() )
				.setProperty( "hibernate.hbm2ddl.auto", "create-drop" )
				.buildSessionFactory();

		Session sess = sf.openSession();
		try {
			sess.getTransaction().begin();
			Investor myInv = getInvestor();
			myInv.setId( 2L );

			sess.save( myInv );
			sess.flush();
			sess.clear();

			Investor inv = (Investor) sess.get( Investor.class, 2L );
			assertEquals( new BigDecimal( "100" ), inv.getInvestments().get( 0 ).getAmount().getAmount() );
		}catch (Exception e){
			sess.getTransaction().rollback();
			throw e;
		}
		finally {
			sess.close();
			sf.close();
		}
	}
