		private Currency determineCurrency(String text, Locale locale) {
			try {
				if (text.length() < 3) {
					// Could not possibly contain a currency code ->
					// try with locale and likely let it fail on parse.
					return Currency.getInstance(locale);
				}
				else if (this.pattern.startsWith(CURRENCY_CODE_PATTERN)) {
					return Currency.getInstance(text.substring(0, 3));
				}
				else if (this.pattern.endsWith(CURRENCY_CODE_PATTERN)) {
					return Currency.getInstance(text.substring(text.length() - 3));
				}
				else {
					// A pattern without a currency code...
					return Currency.getInstance(locale);
				}
			}
			catch (IllegalArgumentException ex) {
				throw new IllegalArgumentException("Cannot determine currency for number value [" + text + "]", ex);
			}
		}
