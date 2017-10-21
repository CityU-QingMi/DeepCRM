	public static SupportedOrmXsdVersion parse(String name, Origin origin) {
		if ( "1.0".equals( name ) ) {
			return ORM_1_0;
		}
		else if ( "2.0".equals( name ) ) {
			return ORM_2_0;
		}
		else if ( "2.1".equals( name ) ) {
			return ORM_2_1;
		}
		else if ( "2.1.0".equals( name ) ) {
			return ORM_2_1_0;
		}
		else if ( "4.0".equals( name ) ) {
			return HBM_4_0;
		}
		throw new UnsupportedOrmXsdVersionException( name, origin );
	}
