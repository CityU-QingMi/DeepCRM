		public IntegralDataTypeHolder getNextValue() {
			try {
				if ( timesCalled == 0 ) {
					initValue();
					return value.copy();
				}
				else {
					return value.add( increment ).copy();
				}
			}
			finally {
				timesCalled++;
			}
		}
