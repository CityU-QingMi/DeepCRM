	public void reThrowAnyErrors() {
		if ( error != null ) {
			if ( RuntimeException.class.isInstance( error ) ) {
				throw RuntimeException.class.cast( error );
			}
			else if ( Error.class.isInstance( error ) ) {
				throw Error.class.cast(  error );
			}
			else {
				throw new ExceptionWrapper( error );
			}
		}
	}
