	@SuppressWarnings({ "", "" })
	public T deepCopyNotNull(T value) {
		if ( ! value.getClass().isArray() ) {
			// ugh!  cannot find a way to properly define the type signature here to
			throw new IllegalArgumentException( "Value was not an array [" + value.getClass().getName() + "]" );
		}
		final int length = Array.getLength( value );
		T copy = (T) Array.newInstance( value.getClass().getComponentType(), length );
		System.arraycopy( value, 0, copy, 0, length );
		return copy;
	}
