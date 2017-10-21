	@Override
	public boolean startingAttribute(AttributeDefinition attributeDefinition) {
		System.out.println(
				String.format(
						"%s Handling attribute (%s)",
						StringHelper.repeat( ">>", depth + 1 ),
						attributeDefinition.getName()
				)
		);
		return true;
	}
