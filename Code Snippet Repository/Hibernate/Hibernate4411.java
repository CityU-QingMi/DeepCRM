	private <K, V> MapJoinImplementor<X, K, V> constructJoin(MapAttribute<? super X, K, V> map, JoinType jt) {
		if ( jt.equals( JoinType.RIGHT ) ) {
			throw new UnsupportedOperationException( "RIGHT JOIN not supported" );
		}

		// TODO : runtime check that the attribute in fact belongs to this From's model/bindable

		final Class<V> attributeType = map.getBindableJavaType();
		return new MapAttributeJoin<X, K, V>( criteriaBuilder(), attributeType, this, map, jt );
	}
