	private static FumCompositeID fumKey(String str, boolean aCompositeQueryTest) {
		FumCompositeID id = new FumCompositeID();
		id.setString( str );

		if (aCompositeQueryTest) {
			id.setShort( fumKeyShort++ );
		}
		else {
			id.setShort( (short) 12 );
		}

		return id;
	}
