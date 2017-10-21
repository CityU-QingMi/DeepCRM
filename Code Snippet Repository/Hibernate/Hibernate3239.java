	public static <T> String join(Collection<T> values, Renderer<T> renderer) {
		final StringBuilder buffer = new StringBuilder();
		boolean firstPass = true;
		for ( T value : values ) {
			if ( firstPass ) {
				firstPass = false;
			}
			else {
				buffer.append( ", " );
			}

			buffer.append( renderer.render( value ) );
		}

		return buffer.toString();
	}
