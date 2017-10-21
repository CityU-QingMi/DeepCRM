	public static Attribute.PersistentAttributeType determineSingularAssociationAttributeType(Member member) {
		if ( Field.class.isInstance( member ) ) {
			return ( (Field) member ).getAnnotation( OneToOne.class ) != null
					? Attribute.PersistentAttributeType.ONE_TO_ONE
					: Attribute.PersistentAttributeType.MANY_TO_ONE;
		}
		else if ( MapMember.class.isInstance( member ) ) {
			return Attribute.PersistentAttributeType.MANY_TO_ONE; // curious to see how this works for non-annotated methods
		}
		else {
			return ( (Method) member ).getAnnotation( OneToOne.class ) != null
					? Attribute.PersistentAttributeType.ONE_TO_ONE
					: Attribute.PersistentAttributeType.MANY_TO_ONE;
		}
	}
