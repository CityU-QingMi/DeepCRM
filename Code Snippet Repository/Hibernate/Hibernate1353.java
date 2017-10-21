	public AccessType getDefaultAccess() throws MappingException {
		AccessType accessType = defaultAccess;

		AccessType hibernateAccessType = AccessType.DEFAULT;
		AccessType jpaAccessType = AccessType.DEFAULT;

		org.hibernate.annotations.AccessType accessTypeAnnotation = property.getAnnotation( org.hibernate.annotations.AccessType.class );
		if ( accessTypeAnnotation != null ) {
			hibernateAccessType = AccessType.getAccessStrategy( accessTypeAnnotation.value() );
		}

		Access access = property.getAnnotation( Access.class );
		if ( access != null ) {
			jpaAccessType = AccessType.getAccessStrategy( access.value() );
		}

		if ( hibernateAccessType != AccessType.DEFAULT
				&& jpaAccessType != AccessType.DEFAULT
				&& hibernateAccessType != jpaAccessType ) {

			StringBuilder builder = new StringBuilder();
			builder.append( property.toString() );
			builder.append(
					" defines @AccessType and @Access with contradicting values. Use of @Access only is recommended."
			);
			throw new MappingException( builder.toString() );
		}

		if ( hibernateAccessType != AccessType.DEFAULT ) {
			accessType = hibernateAccessType;
		}
		else if ( jpaAccessType != AccessType.DEFAULT ) {
			accessType = jpaAccessType;
		}
		return accessType;
	}
