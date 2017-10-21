	@Override
	public void start() {
		if ( ! fetchSourceStack.isEmpty() ) {
			throw new WalkingException(
					"Fetch owner stack was not empty on start; " +
							"be sure to not use LoadPlanBuilderStrategy instances concurrently"
			);
		}
		propertyPathStack.push( new PropertyPath() );
	}
