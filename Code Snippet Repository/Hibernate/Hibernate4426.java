	private <Y> SetJoinImplementor<X, Y> constructJoin(SetAttribute<? super X, Y> set, JoinType jt) {
		if ( jt.equals( JoinType.RIGHT ) ) {
			throw new UnsupportedOperationException( "RIGHT JOIN not supported" );
		}

		// TODO : runtime check that the attribute in fact belongs to this From's model/bindable

		final Class<Y> attributeType = set.getBindableJavaType();
		return new SetAttributeJoin<X, Y>( criteriaBuilder(), attributeType, this, set, jt );
	}
