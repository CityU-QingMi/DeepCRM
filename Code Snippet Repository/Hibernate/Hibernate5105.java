		private Enum fromOrdinal(int ordinal) {
			final Enum[] enumsByOrdinal = enumsByOrdinal();
			if ( ordinal < 0 || ordinal >= enumsByOrdinal.length ) {
				throw new IllegalArgumentException(
						String.format(
								"Unknown ordinal value [%s] for enum class [%s]",
								ordinal,
								enumClass.getName()
						)
				);
			}
			return enumsByOrdinal[ordinal];

		}
