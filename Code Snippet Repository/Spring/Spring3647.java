	@Test
	public void testAmountWithNumberFormat2() {
		FormattedMoneyHolder2 bean = new FormattedMoneyHolder2();
		DataBinder binder = new DataBinder(bean);
		binder.setConversionService(conversionService);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("amount", "10.50");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("10.5", binder.getBindingResult().getFieldValue("amount"));
		assertTrue(bean.getAmount().getNumber().doubleValue() == 10.5d);
		assertEquals("USD", bean.getAmount().getCurrency().getCurrencyCode());
	}
