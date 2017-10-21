	public static Serializable[] extractNonNull(Serializable[] array) {
		final int nonNullCount = countNonNull( array );
		final Serializable[] result = new Serializable[nonNullCount];
		int i = 0;
		for ( Serializable element : array ) {
			if ( element != null ) {
				result[i++] = element;
			}
		}
		if ( i != nonNullCount ) {
			throw new HibernateException( "Number of non-null elements varied between iterations" );
		}
		return result;
	}
