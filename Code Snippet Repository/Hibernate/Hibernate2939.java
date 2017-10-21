	protected int determineAdjustedIncrementSize(String optimizationStrategy, int incrementSize) {
		final int resolvedIncrementSize;
		if ( Math.abs( incrementSize ) > 1 &&
				StandardOptimizerDescriptor.NONE.getExternalName().equals( optimizationStrategy ) ) {
			if ( incrementSize < -1 ) {
				resolvedIncrementSize = -1;
				LOG.honoringOptimizerSetting(
						StandardOptimizerDescriptor.NONE.getExternalName(),
						INCREMENT_PARAM,
						incrementSize,
						"negative",
						resolvedIncrementSize
				);
			}
			else {
				// incrementSize > 1
				resolvedIncrementSize = 1;
				LOG.honoringOptimizerSetting(
						StandardOptimizerDescriptor.NONE.getExternalName(),
						INCREMENT_PARAM,
						incrementSize,
						"positive",
						resolvedIncrementSize
				);
			}
		}
		else {
			resolvedIncrementSize = incrementSize;
		}
		return resolvedIncrementSize;
	}
