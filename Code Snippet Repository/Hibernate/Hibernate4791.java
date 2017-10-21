	public static Action interpretHbm2ddlSetting(Object value) {
		if ( value == null ) {
			return NONE;
		}

		if ( Action.class.isInstance( value ) ) {
			return hbm2ddlSetting( (Action) value );
		}

		final String name = value.toString();
		if ( StringHelper.isEmpty( name ) || NONE.externalJpaName.equals( name ) ) {
			// default is NONE
			return NONE;
		}

		// prefer hbm2ddl names
		for ( Action action : values() ) {
			if ( action.externalHbm2ddlName == null ) {
				continue;
			}

			if ( action.externalHbm2ddlName.equals( name ) ) {
				return hbm2ddlSetting( action );
			}
		}

		// then check JPA external names
		for ( Action action : values() ) {
			if ( action.externalJpaName == null ) {
				continue;
			}

			if ( action.externalJpaName.equals( name ) ) {
				return hbm2ddlSetting( action );
			}
		}

		throw new IllegalArgumentException( "Unrecognized legacy `hibernate.hbm2ddl.auto` value : " + value );
	}
