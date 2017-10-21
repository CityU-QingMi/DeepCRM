	@Override
	public void traceIn(String ruleName, AST tree) {
		if ( !LOG.isTraceEnabled() ) {
			return;
		}
		if ( inputState.guessing > 0 ) {
			return;
		}
		String prefix = StringHelper.repeat( '-', ( traceDepth++ * 2 ) ) + "-> ";
		String traceText = ruleName + " (" + buildTraceNodeName( tree ) + ")";
		LOG.trace( prefix + traceText );
	}
