	@Override
	public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
		// JPA states we should use the following as default:
		//		"The concatenated names of the two associated primary entity tables (owning side
		//		first), separated by an underscore."
		// aka:
		// 		{OWNING SIDE PRIMARY TABLE NAME}_{NON-OWNING SIDE PRIMARY TABLE NAME}
		final String name = source.getOwningPhysicalTableName()
				+ '_'
				+ source.getNonOwningPhysicalTableName();

		return toIdentifier( name, source.getBuildingContext() );
	}
