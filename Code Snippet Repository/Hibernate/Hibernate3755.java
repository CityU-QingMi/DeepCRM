	@Override
	public ExpandingCompositeQuerySpace makeCompositeQuerySpace(
			String uid,
			CompositePropertyMapping compositePropertyMapping,
			boolean canJoinsBeRequired) {

		checkQuerySpaceDoesNotExist( uid );

		final ExpandingCompositeQuerySpace space = new CompositeQuerySpaceImpl(
				compositePropertyMapping,
				uid,
				this,
				canJoinsBeRequired
		);
		registerQuerySpace( space );

		return space;
	}
