	@Test
	public void testRevisionHistory() {
		final Header rev1 = getAuditReader().find( Header.class, headerId, 1 );
		assertEquals( 2, rev1.getEmbeddableWithCollection().getItems().size() );
		assertEquals( "h1-item0", rev1.getEmbeddableWithCollection().getItems().get( 0 ).getName() );
		assertEquals( "h1-item1", rev1.getEmbeddableWithCollection().getItems().get( 1 ).getName() );

		final Header rev2 = getAuditReader().find( Header.class, headerId, 2 );
		assertEquals( 3, rev2.getEmbeddableWithCollection().getItems().size() );
		assertEquals( "h1-item0", rev2.getEmbeddableWithCollection().getItems().get( 0 ).getName() );
		assertEquals( "h1-item1", rev2.getEmbeddableWithCollection().getItems().get( 1 ).getName() );
		assertEquals( "h1-item2", rev2.getEmbeddableWithCollection().getItems().get( 2 ).getName() );

		final Header rev3 = getAuditReader().find( Header.class, headerId, 3 );
		assertEquals( 2, rev3.getEmbeddableWithCollection().getItems().size() );
		assertEquals( "h1-item1", rev3.getEmbeddableWithCollection().getItems().get( 0 ).getName() );
		assertEquals( "h1-item2", rev3.getEmbeddableWithCollection().getItems().get( 1 ).getName() );

		final Header rev4 = getAuditReader().find( Header.class, headerId, 4 );
		assertEquals( 0, rev4.getEmbeddableWithCollection().getItems().size() );
	}
