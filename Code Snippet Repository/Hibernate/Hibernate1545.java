	@SuppressWarnings("")
	private static boolean applyNotNull(Property property, ConstraintDescriptor<?> descriptor) {
		boolean hasNotNull = false;
		if ( NotNull.class.equals( descriptor.getAnnotation().annotationType() ) ) {
			// single table inheritance should not be forced to null due to shared state
			if ( !( property.getPersistentClass() instanceof SingleTableSubclass ) ) {
				//composite should not add not-null on all columns
				if ( !property.isComposite() ) {
					final Iterator<Selectable> itr = property.getColumnIterator();
					while ( itr.hasNext() ) {
						final Selectable selectable = itr.next();
						if ( Column.class.isInstance( selectable ) ) {
							Column.class.cast( selectable ).setNullable( false );
						}
						else {
							LOG.debugf(
									"@NotNull was applied to attribute [%s] which is defined (at least partially) " +
											"by formula(s); formula portions will be skipped",
									property.getName()
							);
						}
					}
				}
			}
			hasNotNull = true;
		}
		return hasNotNull;
	}
