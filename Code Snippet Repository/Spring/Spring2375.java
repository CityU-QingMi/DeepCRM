	private Formatter<Number> configureFormatterFrom(NumberFormat annotation) {
		String pattern = resolveEmbeddedValue(annotation.pattern());
		if (StringUtils.hasLength(pattern)) {
			return new NumberStyleFormatter(pattern);
		}
		else {
			Style style = annotation.style();
			if (style == Style.CURRENCY) {
				return new CurrencyStyleFormatter();
			}
			else if (style == Style.PERCENT) {
				return new PercentStyleFormatter();
			}
			else {
				return new NumberStyleFormatter();
			}
		}
	}
