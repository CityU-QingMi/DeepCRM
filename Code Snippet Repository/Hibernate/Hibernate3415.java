	@Override
	public AttributeNodeImpl<T> makeImmutableCopy() {
		return new AttributeNodeImpl<>(
				this.sessionFactory,
				this.managedType,
				this.attribute,
				makeSafeMapCopy( subgraphMap ),
				makeSafeMapCopy( keySubgraphMap )
		);
	}
