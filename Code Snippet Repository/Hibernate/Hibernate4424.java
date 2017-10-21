	private <Y> CollectionJoinImplementor<X, Y> constructJoin(
			CollectionAttribute<? super X, Y> collection,
			JoinType jt) {
		if ( jt.equals( JoinType.RIGHT ) ) {
			throw new UnsupportedOperationException( "RIGHT JOIN not supported" );
		}

		// TODO : runtime check that the attribute in fact belongs to this From's model/bindable

		final Class<Y> attributeType = collection.getBindableJavaType();
		return new CollectionAttributeJoin<X, Y>(
				criteriaBuilder(),
				attributeType,
				this,
				collection,
				jt
		);
	}
