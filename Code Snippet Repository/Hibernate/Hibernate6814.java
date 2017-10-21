	@Override
	public boolean equals(Object obj) {
		if ( !( obj instanceof UserPK ) ) {
			return false;
		}
		UserPK userPK = (UserPK) obj;
		SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
		return userKey.equals( userPK.userKey ) && formatter.format( startDate )
				.equals( formatter.format( userPK.startDate ) )
				&& formatter.format( endDate ).equals( formatter.format( userPK.endDate ) );
	}
