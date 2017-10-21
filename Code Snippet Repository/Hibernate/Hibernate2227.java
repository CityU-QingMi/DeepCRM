	@SuppressWarnings("")
	private int guessResultSize(RowSelection rowSelection) {
		if ( rowSelection != null ) {
			final int maxReasonableAllocation = rowSelection.getFetchSize() != null ? rowSelection.getFetchSize().intValue() : 100;
			if ( rowSelection.getMaxRows() != null && rowSelection.getMaxRows().intValue() > 0 ) {
				return Math.min( maxReasonableAllocation, rowSelection.getMaxRows().intValue() );
			}
			else if ( rowSelection.getFetchSize() != null && rowSelection.getFetchSize().intValue() > 0 ) {
				return rowSelection.getFetchSize().intValue();
			}
		}
		return 7;//magic number guessed as a reasonable default.
	}
