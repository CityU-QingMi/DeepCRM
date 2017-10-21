	@Test
	@TestForIssue( jiraKey = "" )
	@RequiresDialect({ H2Dialect.class, Oracle8iDialect.class })
	public void testOrderByReferencingFormulaColumn() {
		Session session = openSession();

		// Populating database with test data.
		session.getTransaction().begin();
		Box box1 = new Box( 1 );
		Item item1 = new Item( 1, "1", box1 );
		Item item2 = new Item( 2, "22", box1 );
		Item item3 = new Item( 3, "2", box1 );
		session.persist( box1 );
		session.persist( item1 );
		session.persist( item2 );
		session.persist( item3 );
		session.flush();
		session.refresh( item1 );
		session.refresh( item2 );
		session.refresh( item3 );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		box1 = (Box) session.get( Box.class, box1.getId() );
		Assert.assertEquals( Arrays.asList( item2, item1, item3 ), box1.getItems() );
		session.getTransaction().commit();

		session.clear();

		// Cleanup data.
		session.getTransaction().begin();
		session.delete( item1 );
		session.delete( item2 );
		session.delete( item3 );
		session.delete( box1 );
		session.getTransaction().commit();

		session.close();
	}
