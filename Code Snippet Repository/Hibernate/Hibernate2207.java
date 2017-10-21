	public static TypeSearchability interpret(short code) {
		switch ( code ) {
			case DatabaseMetaData.typeSearchable: {
				return FULL;
			}
			case DatabaseMetaData.typePredNone: {
				return NONE;
			}
			case DatabaseMetaData.typePredBasic: {
				return BASIC;
			}
			case DatabaseMetaData.typePredChar: {
				return CHAR;
			}
			default: {
				throw new IllegalArgumentException( "Unknown type searchability code [" + code + "] enountered" );
			}
		}
	}
