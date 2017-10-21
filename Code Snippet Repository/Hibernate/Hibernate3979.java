	@Override
	@SuppressWarnings("")
	public Type<?> getIdType() {
		final SingularAttributeImpl id = locateIdAttribute();
		if ( id != null ) {
			return id.getType();
		}

		Set<SingularAttribute<? super X, ?>> idClassAttributes = getIdClassAttributesSafely();
		if ( idClassAttributes != null ) {
			if ( idClassAttributes.size() == 1 ) {
				return idClassAttributes.iterator().next().getType();
			}
		}

		return null;
	}
