		public SourceMock(long initialValue, int increment, int timesCalled) {
			this.increment = increment;
			this.timesCalled = timesCalled;
			if ( timesCalled != 0 ) {
				this.value.initialize( initialValue );
				this.initialValue = 1;
			}
			else {
				this.value.initialize( -1 );
				this.initialValue = initialValue;
			}
		}
