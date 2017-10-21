	public static PermissibleAction interpret(String action) {
		if ( INSERT.externalName.equals( action ) ) {
			return INSERT;
		}
		else if ( UPDATE.externalName.equals( action ) ) {
			return UPDATE;
		}
		else if ( DELETE.externalName.equals( action ) ) {
			return DELETE;
		}
		else if ( READ.externalName.equals( action ) ) {
			return READ;
		}
		else if ( ANY.externalName.equals( action ) ) {
			return ANY;
		}

		throw new IllegalArgumentException( "Unrecognized action : " + action );
	}
