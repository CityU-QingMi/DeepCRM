	public void appendOrderByFragment(String ordering) {
		if ( this.orderByClause == null ) {
			this.orderByClause = new StringBuilder();
		}
		else {
			this.orderByClause.append( ", " );
			this.guesstimatedBufferSize += 2;
		}
		this.orderByClause.append( ordering );
	}
