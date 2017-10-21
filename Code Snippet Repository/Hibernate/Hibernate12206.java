	private ElementKind getElementKind(org.hibernate.jpamodelgen.xml.jaxb.AccessType accessType) {
		// if no explicit access type was specified in xml we use the entity access type
		if ( accessType == null ) {
			return TypeUtils.getElementKindForAccessType( accessTypeInfo.getAccessType() );
		}
		if ( org.hibernate.jpamodelgen.xml.jaxb.AccessType.FIELD.equals( accessType ) ) {
			return ElementKind.FIELD;
		}
		else {
			return ElementKind.METHOD;
		}
	}
