		public BaselineAttributeInformation createInformation() {
			return new BaselineAttributeInformation(
					lazy,
					insertable,
					updateable,
					valueGenerationStrategy,
					nullable,
					dirtyCheckable,
					versionable,
					cascadeStyle,
					fetchMode
			);
		}
