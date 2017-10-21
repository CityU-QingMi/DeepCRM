	@Override
	protected Statement withAfterClasses(Statement statement) {
		if ( isAllTestsIgnored() ) {
			return super.withAfterClasses( statement );
		}
		return new AfterClassCallbackHandler(
				this,
				super.withAfterClasses( statement )
		);
	}
