	@Override
	public BigDecimal parse(String text, Locale locale) throws ParseException {
		BigDecimal decimal = (BigDecimal) super.parse(text, locale);
		if (this.roundingMode != null) {
			decimal = decimal.setScale(this.fractionDigits, this.roundingMode);
		}
		else {
			decimal = decimal.setScale(this.fractionDigits);
		}
		return decimal;
	}
