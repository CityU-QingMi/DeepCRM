	private AccessType determineLocalClassDefinedAccessStrategy() {
		AccessType classDefinedAccessType;

		AccessType hibernateDefinedAccessType = AccessType.DEFAULT;
		AccessType jpaDefinedAccessType = AccessType.DEFAULT;

		org.hibernate.annotations.AccessType accessType = xClass.getAnnotation( org.hibernate.annotations.AccessType.class );
		if ( accessType != null ) {
			hibernateDefinedAccessType = AccessType.getAccessStrategy( accessType.value() );
		}

		Access access = xClass.getAnnotation( Access.class );
		if ( access != null ) {
			jpaDefinedAccessType = AccessType.getAccessStrategy( access.value() );
		}

		if ( hibernateDefinedAccessType != AccessType.DEFAULT
				&& jpaDefinedAccessType != AccessType.DEFAULT
				&& hibernateDefinedAccessType != jpaDefinedAccessType ) {
			throw new MappingException(
					"@AccessType and @Access specified with contradicting values. Use of @Access only is recommended. "
			);
		}

		if ( hibernateDefinedAccessType != AccessType.DEFAULT ) {
			classDefinedAccessType = hibernateDefinedAccessType;
		}
		else {
			classDefinedAccessType = jpaDefinedAccessType;
		}
		return classDefinedAccessType;
	}
