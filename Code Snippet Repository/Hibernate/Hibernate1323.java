	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof FkSecondPass ) ) return false;

		FkSecondPass that = (FkSecondPass) o;

		if ( uniqueCounter != that.uniqueCounter ) return false;

		return true;
	}
