	@Test
	public void testEscapeEntityField() {
		Table table = metadata().getEntityBinding(
				"org.hibernate.envers.test.integration.naming.quotation.QuotedFieldsEntity_AUD"
		).getTable();
		Column column1 = getColumnByName( table, "id" );
		Column column2 = getColumnByName( table, "data1" );
		Column column3 = getColumnByName( table, "data2" );
		assert column1 != null;
		assert column2 != null;
		assert column3 != null;
		assert column1.isQuoted();
		assert column2.isQuoted();
		assert column3.isQuoted();
	}
