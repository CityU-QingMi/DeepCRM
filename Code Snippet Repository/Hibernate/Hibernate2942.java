	public static StandardOptimizerDescriptor fromExternalName(String externalName) {
		if ( StringHelper.isEmpty( externalName ) ) {
			log.debug( "No optimizer specified, using NONE as default" );
			return NONE;
		}
		else if ( NONE.externalName.equals( externalName ) ) {
			return NONE;
		}
		else if ( HILO.externalName.equals( externalName ) ) {
			return HILO;
		}
		else if ( LEGACY_HILO.externalName.equals( externalName ) ) {
			return LEGACY_HILO;
		}
		else if ( POOLED.externalName.equals( externalName ) ) {
			return POOLED;
		}
		else if ( POOLED_LO.externalName.equals( externalName ) ) {
			return POOLED_LO;
		}
		else if ( POOLED_LOTL.externalName.equals( externalName ) ) {
			return POOLED_LOTL;
		}
		else {
			log.debugf( "Unknown optimizer key [%s]; returning null assuming Optimizer impl class name", externalName );
			return null;
		}
	}
