	public String getTypeName(int code, int length, int precision, int scale) throws HibernateException {
/**/
/**/
/**/
/**/
		float f = precision > 0 ? (float) scale / (float) precision : 0;
		int p = ( precision > 38 ? 38 : precision );
		int s = ( precision > 38 ? (int) ( 38.0 * f ) : ( scale > 38 ? 38 : scale ) );
		return super.getTypeName( code, length, p, s );
	}
