	@Override
	public String toString() {
		final StringBuilder buf = new StringBuilder()
			.append( "NaturalIdCacheStatistics" )
			.append( "[hitCount=" ).append( this.hitCount )
			.append( ",missCount=" ).append( this.missCount )
			.append( ",putCount=" ).append( this.putCount )
			.append( ",executionCount=" ).append( this.executionCount )
			.append( ",executionAvgTime=" ).append( this.getExecutionAvgTime() )
			.append( ",executionMinTime=" ).append( this.executionMinTime )
			.append( ",executionMaxTime=" ).append( this.executionMaxTime );
		// not sure if this would ever be null but wanted to be careful
		if ( this.region != null ) {
			buf.append( ",elementCountInMemory=" ).append( this.getElementCountInMemory() )
				.append( ",elementCountOnDisk=" ).append( this.getElementCountOnDisk() )
				.append( ",sizeInMemory=" ).append( this.getSizeInMemory() );
		}
		buf.append( ']' );
		return buf.toString();
	}
