	private static void checkFilterConditions(Collection collValue) {
		//for now it can't happen, but sometime soon...
		if ( ( collValue.getFilters().size() != 0 || StringHelper.isNotEmpty( collValue.getWhere() ) ) &&
				collValue.getFetchMode() == FetchMode.JOIN &&
				!( collValue.getElement() instanceof SimpleValue ) && //SimpleValue (CollectionOfElements) are always SELECT but it does not matter
				collValue.getElement().getFetchMode() != FetchMode.JOIN ) {
			throw new MappingException(
					"@ManyToMany or @CollectionOfElements defining filter or where without join fetching "
							+ "not valid within collection using join fetching[" + collValue.getRole() + "]"
			);
		}
	}
