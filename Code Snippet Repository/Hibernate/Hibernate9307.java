	@Override
	public void accept(String action) {
		action = action.toLowerCase();

		for ( Category category : Category.values() ) {
			final Matcher matcher = category.getPattern().matcher( action );
			if ( matcher.matches() ) {
				getActions( category ).add( matcher.group( 1 ) );
				return;
			}
		}
	}
