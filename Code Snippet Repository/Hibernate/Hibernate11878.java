		private SpatialRelateFunction(
				final String name,
				final int relation,
				final boolean isOGCStrict,
				OracleSDOSupport sdo) {
			super( name,  StandardBasicTypes.BOOLEAN );
			this.relation = relation;
			this.isOGCStrict = isOGCStrict;
			this.sdo = sdo;
		}
