		public static Action parseCommandLineOption(String actionText) {
			if ( actionText.equalsIgnoreCase( "create" ) ) {
				return CREATE;
			}
			else if ( actionText.equalsIgnoreCase( "drop" ) ) {
				return DROP;
			}
			else if ( actionText.equalsIgnoreCase( "drop-and-create" ) ) {
				return BOTH;
			}
			else {
				return NONE;
			}
		}
