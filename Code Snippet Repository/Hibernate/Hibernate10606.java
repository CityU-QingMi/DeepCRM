	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Item) ) {
			return false;
		}

		Item item = (Item) o;

		if ( getId() != null ? !getId().equals( item.getId() ) : item.getId() != null ) {
			return false;
		}
		if ( getPrice() != null ? !getPrice().equals( item.getPrice() ) : item.getPrice() != null ) {
			return false;
		}

		return true;
	}
