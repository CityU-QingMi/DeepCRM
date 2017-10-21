	@Override
	protected String getCreateSequenceString(String sequenceName, int initialValue, int incrementSize) throws MappingException {
		if ( incrementSize == 0 ) {
			throw new MappingException( "Unable to create the sequence [" + sequenceName + "]: the increment size must not be 0" );
		}

		String createSequenceString = getCreateSequenceString( sequenceName ) + " start with " + initialValue + " increment by " + incrementSize;
		if ( incrementSize > 0 ) {
			if ( initialValue < 1 ) {
				// default minvalue for an ascending sequence is 1
				createSequenceString += " minvalue " + initialValue;
			}
		}
		else if ( incrementSize < 0 ) {
			if ( initialValue > -1 ) {
				// default maxvalue for a descending sequence is -1
				createSequenceString += " maxvalue " + initialValue;
			}
		}
		return createSequenceString;
	}
