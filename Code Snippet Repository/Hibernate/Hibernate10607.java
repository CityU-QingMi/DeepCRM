	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ItemId) ) {
			return false;
		}

		ItemId itemId = (ItemId) o;

		if ( getModel() != null ? !getModel().equals( itemId.getModel() ) : itemId.getModel() != null ) {
			return false;
		}
		if ( getProducer() != null ? !getProducer().equals( itemId.getProducer() ) : itemId.getProducer() != null ) {
			return false;
		}
		if ( getVersion() != null ? !getVersion().equals( itemId.getVersion() ) : itemId.getVersion() != null ) {
			return false;
		}

		return true;
	}
