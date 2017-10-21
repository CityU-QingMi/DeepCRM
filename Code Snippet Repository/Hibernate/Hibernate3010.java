	@Override
	public boolean isReadOnly() {
		if ( ! isReadOnlyInitialized() && getSession() == null ) {
			throw new IllegalStateException(
					"cannot determine readOnly/modifiable setting when it is not initialized and is not initialized and getSession() == null"
			);
		}
		return ( isReadOnlyInitialized() ?
				readOnly :
				getSession().getPersistenceContext().isDefaultReadOnly()
		);
	}
