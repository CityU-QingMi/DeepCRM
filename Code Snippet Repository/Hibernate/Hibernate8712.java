	@Override
	public void associationKeyRegistered(AssociationKey associationKey) {
		System.out.println(
				String.format(
						"%s AssociationKey registered : %s",
						StringHelper.repeat( ">>", depth + 1 ),
						associationKey.toString()
				)
		);
	}
