	public AccessType getExplicitAccessType(XAnnotatedElement element) {
		AccessType accessType = null;

		AccessType hibernateAccessType = null;
		AccessType jpaAccessType = null;

		org.hibernate.annotations.AccessType accessTypeAnnotation = element.getAnnotation( org.hibernate.annotations.AccessType.class );
		if ( accessTypeAnnotation != null ) {
			hibernateAccessType = AccessType.getAccessStrategy( accessTypeAnnotation.value() );
		}

		Access access = element.getAnnotation( Access.class );
		if ( access != null ) {
			jpaAccessType = AccessType.getAccessStrategy( access.value() );
		}

		if ( hibernateAccessType != null && jpaAccessType != null && hibernateAccessType != jpaAccessType ) {
			throw new MappingException(
					"Found @Access and @AccessType with conflicting values on a property in class " + annotatedClass.toString()
			);
		}

		if ( hibernateAccessType != null ) {
			accessType = hibernateAccessType;
		}
		else if ( jpaAccessType != null ) {
			accessType = jpaAccessType;
		}

		return accessType;
	}
