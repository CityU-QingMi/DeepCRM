	@Override
	public void prepareAlias(RenderingContext renderingContext) {
		if ( getAlias() == null ) {
			if ( isCorrelated() ) {
				setAlias( getCorrelationParent().getAlias() );
			}
			else {
				setAlias( renderingContext.generateAlias() );
			}
		}
	}
