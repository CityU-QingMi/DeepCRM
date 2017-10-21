	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof WikiPage) ) {
			return false;
		}

		WikiPage wikiPage = (WikiPage) o;

		if ( content != null ? !content.equals( wikiPage.content ) : wikiPage.content != null ) {
			return false;
		}
		if ( title != null ? !title.equals( wikiPage.title ) : wikiPage.title != null ) {
			return false;
		}

		return true;
	}
