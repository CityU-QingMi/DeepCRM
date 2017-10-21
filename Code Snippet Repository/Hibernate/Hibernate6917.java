	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Entry ) ) return false;

		Entry entry = (Entry) o;

		if ( id != null ? !id.equals( entry.id ) : entry.id != null ) return false;
		if ( tags != null ? !tags.equals( entry.tags ) : entry.tags != null ) return false;

		return true;
	}
