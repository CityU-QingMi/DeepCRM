	@Test
	public void testColumnScalePrecision() {
		Column testColumn = new Column( "wholeNumber" );
		Column scalePrecisionAuditColumn = auditTable.getColumn( testColumn );
		Column scalePrecisionColumn = originalTable.getColumn( testColumn );

		Assert.assertNotNull( scalePrecisionAuditColumn );
		Assert.assertEquals( scalePrecisionColumn.getPrecision(), scalePrecisionAuditColumn.getPrecision() );
		Assert.assertEquals( scalePrecisionColumn.getScale(), scalePrecisionAuditColumn.getScale() );
	}
