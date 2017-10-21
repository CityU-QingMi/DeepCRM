	public void validate(Mapping mapping) throws MappingException {
		assert getKey() != null : "Collection key not bound : " + getRole();
		assert getElement() != null : "Collection element not bound : " + getRole();

		if ( !getKey().isValid( mapping ) ) {
			throw new MappingException(
					"collection foreign key mapping has wrong number of columns: "
							+ getRole()
							+ " type: "
							+ getKey().getType().getName()
			);
		}
		if ( !getElement().isValid( mapping ) ) {
			throw new MappingException(
					"collection element mapping has wrong number of columns: "
							+ getRole()
							+ " type: "
							+ getElement().getType().getName()
			);
		}

		checkColumnDuplication();
	}
