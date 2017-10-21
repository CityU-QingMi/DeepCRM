	private String checkComponentNullability(Object value, CompositeType compositeType) throws HibernateException {
		// IMPL NOTE : we currently skip checking "any" and "many to any" mappings.
		//
		// This is not the best solution.  But atm there is a mismatch between AnyType#getPropertyNullability
		// and the fact that cascaded-saves for "many to any" mappings are not performed until after this nullability
		// check.  So the nullability check fails for transient entity elements with generated identifiers because
		// the identifier is not yet generated/assigned (is null)
		//
		// The more correct fix would be to cascade saves of the many-to-any elements before the Nullability checking

		if ( compositeType.isAnyType() ) {
			return null;
		}

		final boolean[] nullability = compositeType.getPropertyNullability();
		if ( nullability != null ) {
			//do the test
			final Object[] subValues = compositeType.getPropertyValues( value, session );
			final Type[] propertyTypes = compositeType.getSubtypes();
			for ( int i = 0; i < subValues.length; i++ ) {
				final Object subValue = subValues[i];
				if ( !nullability[i] && subValue==null ) {
					return compositeType.getPropertyNames()[i];
				}
				else if ( subValue != null ) {
					final String breakProperties = checkSubElementsNullability( propertyTypes[i], subValue );
					if ( breakProperties != null ) {
						return buildPropertyPath( compositeType.getPropertyNames()[i], breakProperties );
					}
				}
			}
		}
		return null;
	}
