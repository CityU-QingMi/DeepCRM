	public static CtClass getTargetEntityClass(CtClass managedCtClass, CtField persistentField) throws NotFoundException {
		// get targetEntity defined in the annotation
		try {
			OneToOne oto = PersistentAttributesHelper.getAnnotation( persistentField, OneToOne.class );
			OneToMany otm = PersistentAttributesHelper.getAnnotation( persistentField, OneToMany.class );
			ManyToOne mto = PersistentAttributesHelper.getAnnotation( persistentField, ManyToOne.class );
			ManyToMany mtm = PersistentAttributesHelper.getAnnotation( persistentField, ManyToMany.class );

			Class<?> targetClass = null;
			if ( oto != null ) {
				targetClass = oto.targetEntity();
			}
			if ( otm != null ) {
				targetClass = otm.targetEntity();
			}
			if ( mto != null ) {
				targetClass = mto.targetEntity();
			}
			if ( mtm != null ) {
				targetClass = mtm.targetEntity();
			}

			if ( targetClass != null && targetClass != void.class ) {
				return managedCtClass.getClassPool().get( targetClass.getName() );
			}
		}
		catch (NotFoundException ignore) {
		}

		// infer targetEntity from generic type signature
		String inferredTypeName = inferTypeName( managedCtClass, persistentField.getName() );
		return inferredTypeName == null ? null : managedCtClass.getClassPool().get( inferredTypeName );
	}
