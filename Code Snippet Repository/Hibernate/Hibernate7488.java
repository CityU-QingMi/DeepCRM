		@Override
		public List<String> convertToEntityAttribute(String dbData) {
			if ( dbData == null ) {
				return null;
			}

			dbData = dbData.trim();
			if ( dbData.length() == 0 ) {
				return null;
			}

			final List<String> strings = new ArrayList<String>();
			final StringTokenizer tokens = new StringTokenizer( dbData, "," );

			while ( tokens.hasMoreTokens() ) {
				strings.add( tokens.nextToken() );
			}

			return strings;
		}
