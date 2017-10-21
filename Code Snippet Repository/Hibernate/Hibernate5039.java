	public Object getPropertyValue(Object component, int i)
			throws HibernateException {
		if (component == null) {
			component = new Object[propertySpan];
		}
		if ( component instanceof Object[] ) {
			// A few calls to hashCode pass the property values already in an
			// Object[] (ex: QueryKey hash codes for cached queries).
			// It's easiest to just check for the condition here prior to
			// trying reflection.
			return ( (Object[]) component )[i];
		}
		else {
			return componentTuplizer.getPropertyValue( component, i );
		}
	}
