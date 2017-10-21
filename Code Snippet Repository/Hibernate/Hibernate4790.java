	public static Action interpretJpaSetting(Object value) {
		if ( value == null ) {
			return NONE;
		}

		if ( Action.class.isInstance( value ) ) {
			return (Action) value;
		}

		final String name = value.toString();
		if ( StringHelper.isEmpty( name ) || NONE.externalJpaName.equals( name ) ) {
			// default is NONE
			return NONE;
		}

		// prefer JPA external names
		for ( Action action : values() ) {
			if ( action.externalJpaName == null ) {
				continue;
			}

			if ( action.externalJpaName.equals( name ) ) {
				return action;
			}
		}

		// then check hbm2ddl names
		for ( Action action : values() ) {
			if ( action.externalHbm2ddlName == null ) {
				continue;
			}

			if ( action.externalHbm2ddlName.equals( name ) ) {
				return action;
			}
		}

		throw new IllegalArgumentException( "Unrecognized JPA schema generation action value : " + value );
	}
