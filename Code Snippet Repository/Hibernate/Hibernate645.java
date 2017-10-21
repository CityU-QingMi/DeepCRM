		@Override
		public String determineGeneratorName(GenerationType generationType, GeneratorNameDeterminationContext context) {
			switch ( generationType ) {
				case IDENTITY: {
					return "identity";
				}
				case SEQUENCE: {
					return "seqhilo";
				}
				case TABLE: {
					return MultipleHiLoPerTableGenerator.class.getName();
				}
				default: {
					// AUTO
					final Class javaType = context.getIdType();
					if ( UUID.class.isAssignableFrom( javaType ) ) {
						return UUIDGenerator.class.getName();
					}
					else {
						return "native";
					}
				}
			}
		}
