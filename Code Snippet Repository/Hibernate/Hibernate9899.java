	private ModificationStore getDefaultAudited(XClass clazz) {
		final Audited defaultAudited = clazz.getAnnotation( Audited.class );

		if ( defaultAudited != null ) {
			return defaultAudited.modStore();
		}
		else {
			return null;
		}
	}
