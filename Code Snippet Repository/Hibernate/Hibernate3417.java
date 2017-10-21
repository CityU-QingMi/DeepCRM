	@Override
	@SuppressWarnings("")
	protected Attribute<T,?> resolveAttribute(String attributeName) {
		final Attribute attribute = entityType.getAttribute( attributeName );
		if ( attribute == null ) {
			throw new IllegalArgumentException(
					String.format(
							"Given attribute name [%s] is not an attribute on this entity [%s]",
							attributeName,
							entityType.getName()
					)
			);
		}
		return attribute;
	}
