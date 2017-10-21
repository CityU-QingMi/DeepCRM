			@Override
			public LongList fromString(String string) {
				if ( string == null || "null".equals( string ) ) {
					return null;
				}

				if ( string.length() <= 2 ) {
					return new LongList();
				}

				String[] parts = string.substring( 1, string.length() - 1 ).split( "," );
				LongList results = new LongList( parts.length );

				for ( String part : parts ) {
					results.add( Long.valueOf( part ) );
				}

				return results;
			}
