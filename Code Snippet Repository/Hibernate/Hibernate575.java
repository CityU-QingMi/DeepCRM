	public static CacheConcurrencyStrategy parse(String name) {
		if ( READ_ONLY.isMatch( name ) ) {
			return READ_ONLY;
		}
		else if ( READ_WRITE.isMatch( name ) ) {
			return READ_WRITE;
		}
		else if ( NONSTRICT_READ_WRITE.isMatch( name ) ) {
			return NONSTRICT_READ_WRITE;
		}
		else if ( TRANSACTIONAL.isMatch( name ) ) {
			return TRANSACTIONAL;
		}
		else if ( NONE.isMatch( name ) ) {
			return NONE;
		}
		else {
			return null;
		}
	}
