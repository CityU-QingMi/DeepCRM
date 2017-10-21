		private Enum fromName(String name) {
			try {
				if (name == null) {
					return null;
				}
				return Enum.valueOf( enumClass, name.trim() );
			}
			catch ( IllegalArgumentException iae ) {
				throw new IllegalArgumentException(
						String.format(
								"Unknown name value [%s] for enum class [%s]",
								name,
								enumClass.getName()
						)
				);
			}
		}
