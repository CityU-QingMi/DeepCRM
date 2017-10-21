	private static TypeDescription entityType(TypeDescription.Generic type) {
		if ( type.getSort().isParameterized() ) {
			if ( type.asErasure().isAssignableTo( Collection.class ) ) {
				return type.getTypeArguments().get( 0 ).asErasure();
			}
			if ( type.asErasure().isAssignableTo( Map.class ) ) {
				return type.getTypeArguments().get( 1 ).asErasure();
			}
		}

		return type.asErasure();
	}
