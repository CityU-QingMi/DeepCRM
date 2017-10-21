	@Override
	public URL locateResource(String name) {
		// first we try name as a URL
		try {
			return new URL( name );
		}
		catch (Exception ignore) {
		}

		try {
			final URL url = getAggregatedClassLoader().getResource( name );
			if ( url != null ) {
				return url;
			}
		}
		catch (Exception ignore) {
		}

		if ( name.startsWith( "/" ) ) {
			name = name.substring( 1 );

			try {
				final URL url = getAggregatedClassLoader().getResource( name );
				if ( url != null ) {
					return url;
				}
			}
			catch (Exception ignore) {
			}
		}

		return null;
	}
