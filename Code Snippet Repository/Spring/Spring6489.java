	@Test
	public void testAddInvoiceFuncWithMetaDataUsingArrayParams() throws Exception {
		initializeAddInvoiceWithMetaData(true);
		SimpleJdbcCall adder = new SimpleJdbcCall(dataSource).withFunctionName("add_invoice");
		Number newId = adder.executeFunction(Number.class, 1103, 3);
		assertEquals(4, newId.intValue());
		verifyAddInvoiceWithMetaData(true);
		verify(connection, atLeastOnce()).close();

	}
