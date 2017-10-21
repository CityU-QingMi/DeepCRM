	@Test
	public void testAddInvoiceProcWithoutMetaDataUsingMapParamSource() throws Exception {
		initializeAddInvoiceWithoutMetaData(false);
		SimpleJdbcCall adder = new SimpleJdbcCall(dataSource).withProcedureName("add_invoice");
		adder.declareParameters(
				new SqlParameter("amount", Types.INTEGER),
				new SqlParameter("custid", Types.INTEGER),
				new SqlOutParameter("newid", Types.INTEGER));
		Number newId = adder.executeObject(Number.class, new MapSqlParameterSource().
				addValue("amount", 1103).
				addValue("custid", 3));
		assertEquals(4, newId.intValue());
		verifyAddInvoiceWithoutMetaData(false);
		verify(connection, atLeastOnce()).close();
	}
