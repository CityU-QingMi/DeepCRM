		public void addAttributeConverterDefinition(AttributeConverterDefinition definition) {
			if ( this.attributeConverterDefinitionsByClass == null ) {
				this.attributeConverterDefinitionsByClass = new HashMap<Class, AttributeConverterDefinition>();
			}

			final Object old = this.attributeConverterDefinitionsByClass.put( definition.getAttributeConverter().getClass(), definition );

			if ( old != null ) {
				throw new AssertionFailure(
						String.format(
								"AttributeConverter class [%s] registered multiple times",
								definition.getAttributeConverter().getClass()
						)
				);
			}
		}
