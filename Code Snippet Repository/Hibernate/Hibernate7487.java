		@Override
		public List<Integer> convertToEntityAttribute(String dbData) {
			if ( dbData == null ) {
				return null;
			}

			dbData = dbData.trim();
			if ( dbData.length() == 0 ) {
				return null;
			}

			final List<Integer> integers = new ArrayList<Integer>();
			final StringTokenizer tokens = new StringTokenizer( dbData, "," );

			while ( tokens.hasMoreTokens() ) {
				integers.add( Integer.valueOf( tokens.nextToken() ) );
			}

			return integers;
		}
