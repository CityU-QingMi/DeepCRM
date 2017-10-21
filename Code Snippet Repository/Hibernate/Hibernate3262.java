		@SuppressWarnings("")
		private void nonResident() {
			switch ( state ) {
				case LIR_RESIDENT:
					owner.hotSize--;
					// fallthrough
				case HIR_RESIDENT:
					owner.size--;
					break;
			}
			state = Recency.HIR_NONRESIDENT;
		}
