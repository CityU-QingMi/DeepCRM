	public static RevisionType fromRepresentation(Object representation) {
		if ( representation == null || !(representation instanceof Byte) ) {
			return null;
		}

		switch ( (Byte) representation ) {
			case 0: {
				return ADD;
			}
			case 1: {
				return MOD;
			}
			case 2: {
				return DEL;
			}
			default: {
				throw new IllegalArgumentException( "Unknown representation: " + representation );
			}
		}
	}
