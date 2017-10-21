	@Test
	public void testBatching() throws SQLException {

		doInHibernate( this::sessionFactory, session -> {
			SaleDocumentItem saleDocumentItem = new SaleDocumentItem();
			session.persist( saleDocumentItem );

			SaleDocumentSummary saleDocumentsummary = new SaleDocumentSummary();
			session.persist(saleDocumentsummary);

			saleDocumentsummary.addItem( saleDocumentItem );

			Product product = new Product();
			session.persist(product);
			saleDocumentItem.setProduct(product);

			SaleDocument saleDocument = new SaleDocument();
			session.persist(saleDocument);
			saleDocument.addItem( saleDocumentItem );

			SaleDocument correction = new SaleDocument();
			session.persist(correction);

			saleDocument.setCorerctionSubject(correction);
		} );
	}
