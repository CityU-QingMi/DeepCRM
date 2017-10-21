	public String toFragmentString() {
		if ( lockOptions!= null ) {
			if ( aliases.length() == 0) {
				return dialect.getForUpdateString( lockOptions );
			}
			else {
				return dialect.getForUpdateString( aliases.toString(), lockOptions );
			}
		}
		else if ( aliases.length() == 0) {
			if ( lockMode != null ) {
				return dialect.getForUpdateString( lockMode );
			}
			return "";
		}
		// TODO:  pass lockmode
		if(isNowaitEnabled) {
			return dialect.getForUpdateNowaitString( aliases.toString() );
		}
		else if (isSkipLockedEnabled) {
			return dialect.getForUpdateSkipLockedString( aliases.toString() );
		}
		else {
			return dialect.getForUpdateString( aliases.toString() );
		}
	}
