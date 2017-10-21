	protected String concretePropertySelectFragment(String alias, final boolean[] includeProperty) {
		return concretePropertySelectFragment(
				alias,
				new InclusionChecker() {
					public boolean includeProperty(int propertyNumber) {
						return includeProperty[propertyNumber];
					}
				}
		);
	}
