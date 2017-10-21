	@Override
	public <X> Byte[] wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		if ( Byte[].class.isInstance( value ) ) {
			return (Byte[]) value;
		}
		if ( byte[].class.isInstance( value ) ) {
			return wrapBytes( (byte[]) value );
		}
		if ( InputStream.class.isInstance( value ) ) {
			return wrapBytes( DataHelper.extractBytes( (InputStream) value ) );
		}
		if ( Blob.class.isInstance( value ) || DataHelper.isNClob( value.getClass() ) ) {
			try {
				return wrapBytes( DataHelper.extractBytes( ( (Blob) value ).getBinaryStream() ) );
			}
			catch ( SQLException e ) {
				throw new HibernateException( "Unable to access lob stream", e );
			}
		}

		throw unknownWrap( value.getClass() );
	}
