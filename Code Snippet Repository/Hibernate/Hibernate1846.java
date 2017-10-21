	public String getTypeName(int code, int length, int precision, int scale) throws HibernateException {
/**/
/**/
/**/
/**/
		float f = precision > 0 ? ( float ) scale / ( float ) precision : 0;
		int p = ( precision > 18 ? 18 : precision );
		int s = ( precision > 18 ? ( int ) ( 18.0 * f ) : ( scale > 18 ? 18 : scale ) );

		return super.getTypeName( code, length, p, s );
	}
