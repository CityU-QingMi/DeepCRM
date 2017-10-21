	public void validate(Mapping mapping) throws MappingException {
		super.validate( mapping );

		assert getElement() != null : "IndexedCollection index not bound : " + getRole();

		if ( !getIndex().isValid(mapping) ) {
			throw new MappingException(
				"collection index mapping has wrong number of columns: " +
				getRole() +
				" type: " +
				getIndex().getType().getName()
			);
		}
	}
