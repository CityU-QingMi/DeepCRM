			@Override
			public <X> X unwrap(LongList value, Class<X> type, WrapperOptions options) {
				if ( value == null ) {
					return null;
				}

				if ( String.class.isAssignableFrom( type ) ) {
					return (X) this.toString( value );
				}

				throw unknownUnwrap( type );
			}
