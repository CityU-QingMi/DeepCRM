	private static String getMappedByNotManyToMany(FieldDescription target) {
		try {
			AnnotationDescription.Loadable<OneToOne> oto = EnhancerImpl.getAnnotation( target, OneToOne.class );
			if ( oto != null ) {
				return oto.getValue( new MethodDescription.ForLoadedMethod( OneToOne.class.getDeclaredMethod( "mappedBy" ) ) ).resolve( String.class );
			}

			AnnotationDescription.Loadable<OneToMany> otm = EnhancerImpl.getAnnotation( target, OneToMany.class );
			if ( otm != null ) {
				return otm.getValue( new MethodDescription.ForLoadedMethod( OneToMany.class.getDeclaredMethod( "mappedBy" ) ) ).resolve( String.class );
			}

			AnnotationDescription.Loadable<ManyToMany> mtm = EnhancerImpl.getAnnotation( target, ManyToMany.class );
			if ( mtm != null ) {
				return mtm.getValue( new MethodDescription.ForLoadedMethod( ManyToMany.class.getDeclaredMethod( "mappedBy" ) ) ).resolve( String.class );
			}
		}
		catch (NoSuchMethodException ignored) {
		}

		return null;
	}
