	@Test
	public void testAddInvoiceFuncWithMetaDataUsingMapParamSource() throws Exception {
		initializeAddInvoiceWithMetaData(true);
		SimpleJdbcCall adder = new SimpleJdbcCall(dataSource).withFunctionName("add_invoice");
		Number newId = adder.executeFunction(Number.class, new MapSqlParameterSource()
				.addValue("amount", 1103)
				.addValue("custid", 3));
		assertEquals(4, newId.intValue());
		verifyAddInvoiceWithMetaData(true);
		verify(connection, atLeastOnce()).close();

	}
