	private boolean isDialectMatchingRequired(List<RequiresDialect> requiresDialects) {
		boolean foundMatch = false;
		for ( RequiresDialect requiresDialectAnn : requiresDialects ) {
			for ( Class<? extends Dialect> dialectClass : requiresDialectAnn.value() ) {
				foundMatch = requiresDialectAnn.strictMatching()
						? dialectClass.equals( dialect.getClass() )
						: dialectClass.isInstance( dialect );
				if ( foundMatch ) {
					break;
				}
			}
			if ( foundMatch ) {
				break;
			}
		}
		return foundMatch;
	}
