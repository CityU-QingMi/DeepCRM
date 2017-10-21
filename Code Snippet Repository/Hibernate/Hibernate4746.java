		private static Action interpret(boolean justDrop, boolean justCreate) {
			if ( justDrop ) {
				return Action.DROP;
			}
			else if ( justCreate ) {
				return Action.CREATE;
			}
			else {
				return Action.BOTH;
			}
		}
