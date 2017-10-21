	@SuppressWarnings("")
	public <X> T wrap(X value, WrapperOptions options) {
		if ( value == null ) {
			return null;
		}
		else if ( byte[].class.isInstance( value ) ) {
			return fromBytes( (byte[]) value );
		}
		else if ( InputStream.class.isInstance( value ) ) {
			return fromBytes( DataHelper.extractBytes( (InputStream) value ) );
		}
		else if ( Blob.class.isInstance( value ) ) {
			try {
				return fromBytes( DataHelper.extractBytes( ((Blob) value).getBinaryStream() ) );
			}
			catch ( SQLException e ) {
				throw new HibernateException( e );
			}
		}
		else if ( getJavaTypeClass().isInstance( value ) ) {
			return (T) value;
		}
		throw unknownWrap( value.getClass() );
	}
