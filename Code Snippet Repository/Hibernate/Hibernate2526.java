	public SQLStateConverter(final ViolatedConstraintNameExtracter extracter) {
		super();
		final ConversionContext conversionContext = new ConversionContext() {
			@Override
			public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
				return extracter;
			}
		};
		addDelegate( new SQLStateConversionDelegate( conversionContext ) );
	}
