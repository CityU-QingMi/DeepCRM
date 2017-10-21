	public String toString() {
		StringBuilder buf = new StringBuilder()
				.append( "SecondLevelCacheStatistics" )
				.append( "[hitCount=").append( this.hitCount )
				.append( ",missCount=").append( this.missCount )
				.append( ",putCount=").append( this.putCount );
		//not sure if this would ever be null but wanted to be careful
		if ( region != null ) {
			buf.append( ",elementCountInMemory=" ).append( this.getElementCountInMemory() )
					.append( ",elementCountOnDisk=" ).append( this.getElementCountOnDisk() )
					.append( ",sizeInMemory=" ).append( this.getSizeInMemory() );
		}
		buf.append( ']' );
		return buf.toString();
	}
