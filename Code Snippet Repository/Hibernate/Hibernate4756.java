		public static ExportType interpret(boolean justDrop, boolean justCreate) {
			if ( justDrop ) {
				return ExportType.DROP;
			}
			else if ( justCreate ) {
				return ExportType.CREATE;
			}
			else {
				return ExportType.BOTH;
			}
		}
