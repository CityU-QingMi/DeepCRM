	private void setAccess( String access, Default defaultType) {
		AccessType type;
		if ( access != null ) {
			try {
				type = AccessType.valueOf( access );
			}
			catch ( IllegalArgumentException e ) {
				throw new AnnotationException( "Invalid access type " + access + " (check your xml configuration)" );
			}
			defaultType.setAccess( type );
		}
	}
