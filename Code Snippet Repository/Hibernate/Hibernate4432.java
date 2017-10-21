	@Override
	@SuppressWarnings({ "" })
	public <Y> Path<Y> get(SingularAttribute<? super X, Y> attribute) {
		if ( ! canBeDereferenced() ) {
			throw illegalDereference();
		}

		SingularAttributePath<Y> path = (SingularAttributePath<Y>) resolveCachedAttributePath( attribute.getName() );
		if ( path == null ) {
			path = new SingularAttributePath<Y>(
					criteriaBuilder(),
					attribute.getJavaType(),
					getPathSourceForSubPaths(),
					attribute
			);
			registerAttributePath( attribute.getName(), path );
		}
		return path;
	}
