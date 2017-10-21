	public int getIndex(Class[] parameterTypes) {
		final Constructor[] constructors = this.type.getConstructors();
		boolean eq;

		for ( int i = 0; i < constructors.length; ++i ) {
			if ( !Modifier.isPublic( constructors[i].getModifiers() ) ) {
				continue;
			}
			final Class[] params = constructors[i].getParameterTypes();
			if ( params.length != parameterTypes.length ) {
				continue;
			}
			eq = true;
			for ( int j = 0; j < params.length; ++j ) {
				if ( !params[j].equals( parameterTypes[j] ) ) {
					eq = false;
					break;
				}
			}
			if ( eq ) {
				return i;
			}
		}
		return -1;
	}
