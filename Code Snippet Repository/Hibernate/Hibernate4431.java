	protected final RuntimeException illegalDereference() {
		return new IllegalStateException(
				String.format(
						"Illegal attempt to dereference path source [%s] of basic type",
						getPathIdentifier()
				)
		);
//		String message = "Illegal attempt to dereference path source [";
//		if ( source != null ) {
//			message += " [" + getPathIdentifier() + "]";
//		}
//		return new IllegalArgumentException(message);
	}
