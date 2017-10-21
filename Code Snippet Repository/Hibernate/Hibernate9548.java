	@Override
	public void accept(String action) {
		if (formatter != null) {
			action = formatter.format(action);
		}
		if ( delimiter != null ) {
			action += delimiter;
		}
		System.out.println( action );
	}
