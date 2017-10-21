	@Test
	public void testAmountWithNumberFormat3() {
		FormattedMoneyHolder3 bean = new FormattedMoneyHolder3();
		DataBinder binder = new DataBinder(bean);
		binder.setConversionService(conversionService);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("amount", "10%");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("10%", binder.getBindingResult().getFieldValue("amount"));
		assertTrue(bean.getAmount().getNumber().doubleValue() == 0.1d);
		assertEquals("USD", bean.getAmount().getCurrency().getCurrencyCode());
	}
