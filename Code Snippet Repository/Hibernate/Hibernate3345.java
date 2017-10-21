		private void checkBatched(int rowCount, int batchPosition) {
			if ( rowCount == -2 ) {
				LOG.debugf( "Success of batch update unknown: %s", batchPosition );
			}
			else if ( rowCount == -3 ) {
				throw new BatchFailedException( "Batch update failed: " + batchPosition );
			}
			else {
				if ( expectedRowCount > rowCount ) {
					throw new StaleStateException(
							"Batch update returned unexpected row count from update ["
									+ batchPosition + "]; actual row count: " + rowCount
									+ "; expected: " + expectedRowCount
					);
				}
				if ( expectedRowCount < rowCount ) {
					String msg = "Batch update returned unexpected row count from update [" +
							batchPosition + "]; actual row count: " + rowCount +
							"; expected: " + expectedRowCount;
					throw new BatchedTooManyRowsAffectedException( msg, expectedRowCount, rowCount, batchPosition );
				}
			}
		}
