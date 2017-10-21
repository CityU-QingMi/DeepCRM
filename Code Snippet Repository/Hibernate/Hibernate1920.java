		private EnumState(int offset, Class<E> enumType) {
			final E[] enumConstants = enumType.getEnumConstants();

			// In case any of the enums cannot be stored in 4 bits anymore, we'd have to re-structure the compressed
			// state int
			if ( enumConstants.length > 15 ) {
				throw new AssertionFailure( "Cannot store enum type " + enumType.getName() + " in compressed state as"
						+ " it has too many values." );
			}

			this.offset = offset;
			this.enumConstants = enumConstants;

			// a mask for reading the four bits, starting at the right offset
			this.mask = 0xF << offset;

			// a mask for setting the four bits at the right offset to 0
			this.unsetMask = 0xFFFF & ~mask;
		}
