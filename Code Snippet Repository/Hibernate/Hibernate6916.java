	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof BlogEntry ) ) return false;
		if ( !super.equals( o ) ) return false;

		BlogEntry blogEntry = (BlogEntry) o;

		if ( text != null ? !text.equals( blogEntry.text ) : blogEntry.text != null ) return false;

		return true;
	}
