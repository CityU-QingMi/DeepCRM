	@Override
	protected Statement withBeforeClasses(Statement statement) {
		if ( isAllTestsIgnored() ) {
			return super.withBeforeClasses( statement );
		}
		return new BeforeClassCallbackHandler(
				this,
				super.withBeforeClasses( statement )
		);
	}
