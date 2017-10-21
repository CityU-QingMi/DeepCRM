	public static TypeDescription getTargetEntityClass(TypeDescription managedCtClass, FieldDescription persistentField) {
		try {
			AnnotationDescription.Loadable<OneToOne> oto = EnhancerImpl.getAnnotation( persistentField, OneToOne.class );
			AnnotationDescription.Loadable<OneToMany> otm = EnhancerImpl.getAnnotation( persistentField, OneToMany.class );
			AnnotationDescription.Loadable<ManyToOne> mto = EnhancerImpl.getAnnotation( persistentField, ManyToOne.class );
			AnnotationDescription.Loadable<ManyToMany> mtm = EnhancerImpl.getAnnotation( persistentField, ManyToMany.class );

			if ( oto == null && otm == null && mto == null && mtm == null ) {
				return null;
			}

			AnnotationValue<?, ?> targetClass = null;
			if ( oto != null ) {
				targetClass = oto.getValue( new MethodDescription.ForLoadedMethod( OneToOne.class.getDeclaredMethod( "targetEntity" ) ) );
			}
			if ( otm != null ) {
				targetClass = otm.getValue( new MethodDescription.ForLoadedMethod( OneToMany.class.getDeclaredMethod( "targetEntity" ) ) );
			}
			if ( mto != null ) {
				targetClass = mto.getValue( new MethodDescription.ForLoadedMethod( ManyToOne.class.getDeclaredMethod( "targetEntity" ) ) );
			}
			if ( mtm != null ) {
				targetClass = mtm.getValue( new MethodDescription.ForLoadedMethod( ManyToMany.class.getDeclaredMethod( "targetEntity" ) ) );
			}

			if ( targetClass == null ) {
				log.infof(
						"Could not find type of bi-directional association for field [%s#%s]",
						managedCtClass.getName(),
						persistentField.getName()
				);
				return null;
			}
			else if ( !targetClass.resolve( TypeDescription.class ).represents( void.class ) ) {
				return targetClass.resolve( TypeDescription.class );
			}
		}
		catch (NoSuchMethodException ignored) {
		}

		return entityType( target( persistentField ) );
	}
