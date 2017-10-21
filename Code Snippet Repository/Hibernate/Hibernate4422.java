	private <Y> JoinImplementor<X, Y> constructJoin(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		if ( Type.PersistenceType.BASIC.equals( attribute.getType().getPersistenceType() ) ) {
			throw new BasicPathUsageException( "Cannot join to attribute of basic type", attribute );
		}

		// TODO : runtime check that the attribute in fact belongs to this From's model/bindable

		if ( jt.equals( JoinType.RIGHT ) ) {
			throw new UnsupportedOperationException( "RIGHT JOIN not supported" );
		}

		final Class<Y> attributeType = attribute.getBindableJavaType();
		return new SingularAttributeJoin<X, Y>(
				criteriaBuilder(),
				attributeType,
				this,
				attribute,
				jt
		);
	}
