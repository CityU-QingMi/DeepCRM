	@Override
	public int compareTo(DelayedPostInsertIdentifier that) {
		if ( this.identifier < that.identifier ) {
			return -1;
		}
		else if ( this.identifier > that.identifier ) {
			return 1;
		}
		else {
			return 0;
		}
	}
