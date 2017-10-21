	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Document ) ) return false;

		Document document = (Document) o;

		if ( fullText != null ? !fullText.equals( document.fullText ) : document.fullText != null ) return false;
		if ( id != null ? !id.equals( document.id ) : document.id != null ) return false;
		if ( revision != null ? !revision.equals( document.revision ) : document.revision != null ) return false;
		if ( shortDescription != null ? !shortDescription.equals( document.shortDescription ) : document.shortDescription != null ) return false;

		return true;
	}
