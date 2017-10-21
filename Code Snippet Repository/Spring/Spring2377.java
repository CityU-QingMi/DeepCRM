	private Formatter<MonetaryAmount> configureFormatterFrom(NumberFormat annotation) {
		String pattern = resolveEmbeddedValue(annotation.pattern());
		if (StringUtils.hasLength(pattern)) {
			return new PatternDecoratingFormatter(pattern);
		}
		else {
			Style style = annotation.style();
			if (style == Style.NUMBER) {
				return new NumberDecoratingFormatter(new NumberStyleFormatter());
			}
			else if (style == Style.PERCENT) {
				return new NumberDecoratingFormatter(new PercentStyleFormatter());
			}
			else {
				return new NumberDecoratingFormatter(new CurrencyStyleFormatter());
			}
		}
	}
