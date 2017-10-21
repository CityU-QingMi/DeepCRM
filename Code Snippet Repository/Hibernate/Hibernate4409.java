	private <Y> ListJoinImplementor<X, Y> constructJoin(ListAttribute<? super X, Y> list, JoinType jt) {
		if ( jt.equals( JoinType.RIGHT ) ) {
			throw new UnsupportedOperationException( "RIGHT JOIN not supported" );
		}

		// TODO : runtime check that the attribute in fact belongs to this From's model/bindable

		final Class<Y> attributeType = list.getBindableJavaType();
		return new ListAttributeJoin<X, Y>( criteriaBuilder(), attributeType, this, list, jt );
	}
