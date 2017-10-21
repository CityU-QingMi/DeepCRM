	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Item ) ) return false;

		Item item = (Item) o;

		if ( id != item.id ) return false;
		if ( sortField != item.sortField ) return false;
		if ( code != null ? !code.equals( item.code ) : item.code != null ) return false;

		return true;
	}
