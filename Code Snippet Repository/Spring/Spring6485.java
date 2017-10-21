	@Test
	public void testAddInvoiceProcWithMetaDataUsingMapParamSource() throws Exception {
		initializeAddInvoiceWithMetaData(false);
		SimpleJdbcCall adder = new SimpleJdbcCall(dataSource).withProcedureName("add_invoice");
		Number newId = adder.executeObject(Number.class, new MapSqlParameterSource()
				.addValue("amount", 1103)
				.addValue("custid", 3));
		assertEquals(4, newId.intValue());
		verifyAddInvoiceWithMetaData(false);
		verify(connection, atLeastOnce()).close();
	}
