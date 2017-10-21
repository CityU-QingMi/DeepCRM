	@Override
	public boolean areEqual(T one, T another) {
		if ( one == another ) {
			return true;
		}
		if ( one == null || another == null ) {
			return false;
		}
		return one.equals( another )
				|| PrimitiveByteArrayTypeDescriptor.INSTANCE.areEqual( toBytes( one ), toBytes( another ) );
	}
