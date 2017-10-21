	public static OptimisticLockStyle interpretOldCode(int oldCode) {
		switch ( oldCode ) {
			case -1: {
				return NONE;
			}
			case 0: {
				return VERSION;
			}
			case 1: {
				return DIRTY;
			}
			case 2: {
				return ALL;
			}
			default: {
				throw new IllegalArgumentException( "Illegal legacy optimistic lock style code :" + oldCode );
			}
		}
	}
