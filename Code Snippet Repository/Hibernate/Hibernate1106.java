	private static String getMappedByFromAnnotation(CtField persistentField) {

		OneToOne oto = PersistentAttributesHelper.getAnnotation( persistentField, OneToOne.class );
		if ( oto != null ) {
			return oto.mappedBy();
		}

		OneToMany otm = PersistentAttributesHelper.getAnnotation( persistentField, OneToMany.class );
		if ( otm != null ) {
			return otm.mappedBy();
		}

		// For @ManyToOne associations, mappedBy must come from the @OneToMany side of the association

		ManyToMany mtm = PersistentAttributesHelper.getAnnotation( persistentField, ManyToMany.class );
		return mtm == null ? "" : mtm.mappedBy();
	}
