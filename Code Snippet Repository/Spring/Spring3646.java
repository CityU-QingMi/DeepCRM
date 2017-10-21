	@Test
	public void testAmountWithNumberFormat1() {
		FormattedMoneyHolder1 bean = new FormattedMoneyHolder1();
		DataBinder binder = new DataBinder(bean);
		binder.setConversionService(conversionService);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("amount", "$10.50");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("$10.50", binder.getBindingResult().getFieldValue("amount"));
		assertTrue(bean.getAmount().getNumber().doubleValue() == 10.5d);
		assertEquals("USD", bean.getAmount().getCurrency().getCurrencyCode());

		LocaleContextHolder.setLocale(Locale.CANADA);
		binder.bind(propertyValues);
		LocaleContextHolder.setLocale(Locale.US);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("$10.50", binder.getBindingResult().getFieldValue("amount"));
		assertTrue(bean.getAmount().getNumber().doubleValue() == 10.5d);
		assertEquals("CAD", bean.getAmount().getCurrency().getCurrencyCode());
	}
