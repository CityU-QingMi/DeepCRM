    @Test
    @TestForIssue( jiraKey = "" )
    public void testPersistOnAlreadyPersistentEntityWithUninitializedLazyCollection() {

		final Invoice _invoice = doInHibernate( this::sessionFactory, this::createInvoiceWithTwoInvoiceLines );

		Invoice invoiceAfter = doInHibernate( this::sessionFactory, session -> {
			SessionStatistics stats = session.getStatistics();

			// load invoice, invoiceLines should not be loaded
			Invoice invoice = session.get(Invoice.class, _invoice.getId());
			Assert.assertEquals(
				"Invoice lines should not be initialized while loading the invoice, " +
				"because of the lazy association.", 1, stats.getEntityCount());

			invoice.setName(invoice.getName() + " !");

			return invoice;
		} );

		try {
			invoiceAfter.getInvoiceLines().size();
			fail("Should throw LazyInitializationException");
		}
		catch (LazyInitializationException expected) {
		}
	}
