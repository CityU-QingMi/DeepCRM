	private void processSecondPasses(ArrayList<? extends SecondPass> secondPasses) {
		if ( secondPasses == null ) {
			return;
		}

		for ( SecondPass secondPass : secondPasses ) {
			secondPass.doSecondPass( getEntityBindingMap() );
		}

		secondPasses.clear();
	}
