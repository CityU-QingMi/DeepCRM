		@Override
		public URL convertToEntityAttribute(String dbData) {
			convertToEntityAttributeCalled = true;
			if ( dbData == null ) {
				return null;
			}

			try {
				return new URL( dbData );
			}
			catch (MalformedURLException e) {
				throw new IllegalArgumentException( "Could not convert incoming value to URL : " + dbData );
			}
		}
