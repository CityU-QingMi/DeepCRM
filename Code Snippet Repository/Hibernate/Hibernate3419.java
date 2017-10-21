	@Override
	@SuppressWarnings("")
	protected Attribute<T,?> resolveAttribute(String attributeName) {
		final Attribute<T,?> attribute = managedType.getAttribute( attributeName );
		if ( attribute == null ) {
			throw new IllegalArgumentException(
					String.format(
							"Given attribute name [%s] is not an attribute on this class [%s]",
							attributeName,
							managedType.getClass().getName()
					)
			);
		}
		return attribute;
	}
