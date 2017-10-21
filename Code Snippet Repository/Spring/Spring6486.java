	@Test
	public void testAddInvoiceFuncWithoutMetaDataUsingMapParamSource() throws Exception {
		initializeAddInvoiceWithoutMetaData(true);
		SimpleJdbcCall adder = new SimpleJdbcCall(dataSource).withFunctionName("add_invoice");
		adder.declareParameters(
				new SqlOutParameter("return", Types.INTEGER),
				new SqlParameter("amount", Types.INTEGER),
				new SqlParameter("custid", Types.INTEGER));
		Number newId = adder.executeFunction(Number.class, new MapSqlParameterSource()
				.addValue("amount", 1103)
				.addValue("custid", 3));
		assertEquals(4, newId.intValue());
		verifyAddInvoiceWithoutMetaData(true);
		verify(connection, atLeastOnce()).close();
	}
