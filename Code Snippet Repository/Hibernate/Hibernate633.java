	private boolean typesMatch(ResolvedType converterDefinedType, ResolvedType checkType) {
		if ( !converterDefinedType.getErasedType().isAssignableFrom( checkType.getErasedType() ) ) {
			return false;
		}

		// if the converter did not define any nested type parameters, then the check above is
		// enough for a match
		if ( converterDefinedType.getTypeParameters().isEmpty() ) {
			return true;
		}

		// however, here the converter *did* define nested type parameters, so we'd have a converter defined using something like, e.g., List<String> for its
		// domain type.
		//
		// we need to check those nested types as well

		if ( checkType.getTypeParameters().isEmpty() ) {
			// the domain type did not define nested type params.  a List<String> would not auto-match a List(<Object>)
			return false;
		}

		if ( converterDefinedType.getTypeParameters().size() != checkType.getTypeParameters().size() ) {
			// they had different number of type params somehow.
			return false;
		}

		for ( int i = 0; i < converterDefinedType.getTypeParameters().size(); i++ ) {
			if ( !typesMatch( converterDefinedType.getTypeParameters().get( i ), checkType.getTypeParameters().get( i ) ) ) {
				return false;
			}
		}

		return true;
	}
