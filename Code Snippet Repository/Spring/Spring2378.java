		@Override
		public MonetaryAmount parse(String text, Locale locale) throws ParseException {
			CurrencyStyleFormatter formatter = new CurrencyStyleFormatter();
			Currency currency = determineCurrency(text, locale);
			CurrencyUnit currencyUnit = Monetary.getCurrency(currency.getCurrencyCode());
			formatter.setCurrency(currency);
			formatter.setPattern(this.pattern);
			Number numberValue = formatter.parse(text, locale);
			return Monetary.getDefaultAmountFactory().setNumber(numberValue).setCurrency(currencyUnit).create();
		}
