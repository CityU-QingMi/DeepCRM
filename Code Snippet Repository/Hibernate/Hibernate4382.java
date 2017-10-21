	public ExplicitParameterInfo(String name, Integer position, Class<T> type) {
		if ( name == null && position == null ) {
			throw new IllegalStateException( "Both name and position were null; caller should have generated parameter name" );
		}
		if ( name != null && position != null ) {
			throw new IllegalStateException( "Both name and position were specified" );
		}

		this.name = name;
		this.position = position;
		this.type = type;
	}
