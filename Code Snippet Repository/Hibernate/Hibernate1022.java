		public DuplicateSecondaryTableException(Identifier tableName) {
			super(
					String.format(
							Locale.ENGLISH,
							"Table with that name [%s] already associated with entity",
							tableName.render()
					)
			);
			this.tableName = tableName;
		}
