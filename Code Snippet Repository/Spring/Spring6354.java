		@Override
		public void cleanup() {
			if (this.xmlObject != null) {
				try {
					this.xmlObject.free();
				}
				catch (SQLException ex) {
					throw new DataAccessResourceFailureException("Could not free SQLXML object", ex);
				}
			}
		}
