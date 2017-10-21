	@Test
	@TestForIssue(jiraKey = "")
	public void testFormulaAnnotationWithPartitionBy() {

		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		DisplayItem displayItem20_1 = new DisplayItem();
		displayItem20_1.setId( 1 );
		displayItem20_1.setDiscountCode( "20" );
		displayItem20_1.setDiscountValue( 12.34d );

		DisplayItem displayItem20_2 = new DisplayItem();
		displayItem20_2.setId( 2 );
		displayItem20_2.setDiscountCode( "20" );
		displayItem20_2.setDiscountValue( 15.89 );

		DisplayItem displayItem100 = new DisplayItem();
		displayItem100.setId( 3 );
		displayItem100.setDiscountCode( "100" );
		displayItem100.setDiscountValue( 12.5 );

		session.persist( displayItem20_1 );
		session.persist( displayItem20_2 );
		session.persist( displayItem100 );

		transaction.commit();
		session.close();

		session = openSession();
		transaction = session.beginTransaction();

		List<DisplayItem> displayItems = session.createQuery( "select di from DisplayItem di order by di.id", DisplayItem.class).getResultList();

		assertNotNull( displayItems );
		assertEquals( displayItems.size(), 3 );

		assertEquals( 1, displayItems.get( 0 ).getItemsByCode().intValue() );
		assertEquals( 2, displayItems.get( 1 ).getItemsByCode().intValue() );
		assertEquals( 1, displayItems.get( 2 ).getItemsByCode().intValue() );

		transaction.commit();
		session.close();
	}
