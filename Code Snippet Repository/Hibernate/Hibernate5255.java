	@SuppressWarnings({ "" })
	public <X> X unwrap(T value, Class<X> type, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		else if ( type.isInstance( value ) ) {
			return (X) value;
		}
		else if ( byte[].class.isAssignableFrom( type ) ) {
			return (X) toBytes( value );
		}
		else if ( InputStream.class.isAssignableFrom( type ) ) {
			return (X) new ByteArrayInputStream( toBytes( value ) );
		}
		else if ( BinaryStream.class.isAssignableFrom( type ) ) {
			return (X) new BinaryStreamImpl( toBytes( value ) );
		}
		else if ( Blob.class.isAssignableFrom( type ) ) {
			return (X) options.getLobCreator().createBlob( toBytes( value ) );
		}

		throw unknownUnwrap( type );
	}
