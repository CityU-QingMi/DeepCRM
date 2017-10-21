	protected void doTest() {
		for ( int i = 0; i < NUMBER_INSERTS; i++ ) {
			newEntityManager();
			EntityManager entityManager = getEntityManager();

			entityManager.getTransaction().begin();

			RootEntity re = new RootEntity();
			re.setId( idCounter++ );
			re.setData1( "data1" );
			re.setData2( "data2" );
			re.setDate1( new Date() );
			re.setNumber1( 123 );
			re.setNumber2( 456 );
			re.setChild1( createChildEntity1() );
			re.setChild2( createChildEntity1() );
			re.setChild3( createChildEntity1() );

			start();
			entityManager.persist( re );
			entityManager.getTransaction().commit();
			stop();
		}
	}
