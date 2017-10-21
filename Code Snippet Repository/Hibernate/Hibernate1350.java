	private static boolean discoverTypeWithoutReflection(XProperty p) {
		if ( p.isAnnotationPresent( OneToOne.class ) && !p.getAnnotation( OneToOne.class )
				.targetEntity()
				.equals( void.class ) ) {
			return true;
		}
		else if ( p.isAnnotationPresent( OneToMany.class ) && !p.getAnnotation( OneToMany.class )
				.targetEntity()
				.equals( void.class ) ) {
			return true;
		}
		else if ( p.isAnnotationPresent( ManyToOne.class ) && !p.getAnnotation( ManyToOne.class )
				.targetEntity()
				.equals( void.class ) ) {
			return true;
		}
		else if ( p.isAnnotationPresent( ManyToMany.class ) && !p.getAnnotation( ManyToMany.class )
				.targetEntity()
				.equals( void.class ) ) {
			return true;
		}
		else if ( p.isAnnotationPresent( org.hibernate.annotations.Any.class ) ) {
			return true;
		}
		else if ( p.isAnnotationPresent( ManyToAny.class ) ) {
			if ( !p.isCollection() && !p.isArray() ) {
				throw new AnnotationException( "@ManyToAny used on a non collection non array property: " + p.getName() );
			}
			return true;
		}
		else if ( p.isAnnotationPresent( Type.class ) ) {
			return true;
		}
		else if ( p.isAnnotationPresent( Target.class ) ) {
			return true;
		}
		return false;
	}
