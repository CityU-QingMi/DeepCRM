	@Test
	public void testAmountWithNumberFormat4() {
		FormattedMoneyHolder4 bean = new FormattedMoneyHolder4();
		DataBinder binder = new DataBinder(bean);
		binder.setConversionService(conversionService);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("amount", "010.500");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("010.500", binder.getBindingResult().getFieldValue("amount"));
		assertTrue(bean.getAmount().getNumber().doubleValue() == 10.5d);
		assertEquals("USD", bean.getAmount().getCurrency().getCurrencyCode());
	}
