	@Override
	public void foundCircularAssociation(
			AssociationAttributeDefinition attributeDefinition) {
		System.out.println(
				String.format(
						"%s Handling circular association attribute (%s) : %s",
						StringHelper.repeat( ">>", depth + 1 ),
						attributeDefinition.toString(),
						attributeDefinition.getAssociationKey().toString()
				)
		);
	}
