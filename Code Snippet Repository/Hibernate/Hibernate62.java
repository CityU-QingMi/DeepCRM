		static PhoneNumber fromString(String phoneNumber) {
			String[] tokens = phoneNumber.split( "-" );
			if ( tokens.length != 3 ) {
				throw new IllegalArgumentException( "invalid phone number: " + phoneNumber );
			}
			int i = 0;
			return new MobilePhone(
				tokens[i++],
				tokens[i++],
				tokens[i]
			);
		}
