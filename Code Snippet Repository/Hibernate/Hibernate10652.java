	@Test
	public void testDiscriminatorFormulaInAuditTable() {
		assert parentAudit.getDiscriminator().hasFormula();
		Iterator iterator = parentAudit.getDiscriminator().getColumnIterator();
		while ( iterator.hasNext() ) {
			Object o = iterator.next();
			if ( o instanceof Formula ) {
				Formula formula = (Formula) o;
				Assert.assertEquals( ParentEntity.DISCRIMINATOR_QUERY, formula.getText() );
				return;
			}
		}
		assert false;
	}
