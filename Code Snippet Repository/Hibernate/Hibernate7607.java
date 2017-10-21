	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( ! ( o instanceof Folder ) ) return false;

		Folder folder = (Folder) o;

		if ( id != null ? !id.equals( folder.id ) : folder.id != null ) return false;
		if ( name != null ? !name.equals( folder.name ) : folder.name != null ) return false;
		if ( owner != null ? !owner.equals( folder.owner ) : folder.owner != null ) return false;

		return true;
	}
