	public static void registerCascadeStyle(String name, BaseCascadeStyle cascadeStyle) {
		log.tracef( "Registering external cascade style [%s : %s]", name, cascadeStyle );
		final CascadeStyle old = STYLES.put( name, cascadeStyle );
		if ( old != null ) {
			log.debugf(
					"External cascade style registration [%s : %s] overrode base registration [%s]",
					name,
					cascadeStyle,
					old
			);
		}
	}
