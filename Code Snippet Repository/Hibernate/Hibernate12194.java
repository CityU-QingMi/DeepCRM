	private AccessType mapXmlAccessTypeToJpaAccessType(org.hibernate.jpamodelgen.xml.jaxb.AccessType xmlAccessType) {
		switch ( xmlAccessType ) {
			case FIELD: {
				return AccessType.FIELD;
			}
			case PROPERTY: {
				return AccessType.PROPERTY;
			}
			default: {
			}
		}
		return null;
	}
