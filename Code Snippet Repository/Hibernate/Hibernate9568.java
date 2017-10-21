			@Override
			public <X> LongList wrap(X value, WrapperOptions options) {
				if ( value == null ) {
					return null;
				}

				Class type = value.getClass();

				if ( String.class.isAssignableFrom( type ) ) {
					String s = (String) value;
					return this.fromString( s );
				}

				throw unknownWrap( type );
			}
