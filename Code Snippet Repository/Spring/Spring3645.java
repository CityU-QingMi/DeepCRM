	@Test
	public void testAmountAndUnit() {
		MoneyHolder bean = new MoneyHolder();
		DataBinder binder = new DataBinder(bean);
		binder.setConversionService(conversionService);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("amount", "USD 10.50");
		propertyValues.add("unit", "USD");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("USD10.50", binder.getBindingResult().getFieldValue("amount"));
		assertEquals("USD", binder.getBindingResult().getFieldValue("unit"));
		assertTrue(bean.getAmount().getNumber().doubleValue() == 10.5d);
		assertEquals("USD", bean.getAmount().getCurrency().getCurrencyCode());

		LocaleContextHolder.setLocale(Locale.CANADA);
		binder.bind(propertyValues);
		LocaleContextHolder.setLocale(Locale.US);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("USD10.50", binder.getBindingResult().getFieldValue("amount"));
		assertEquals("USD", binder.getBindingResult().getFieldValue("unit"));
		assertTrue(bean.getAmount().getNumber().doubleValue() == 10.5d);
		assertEquals("USD", bean.getAmount().getCurrency().getCurrencyCode());
	}
