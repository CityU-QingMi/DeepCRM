		@Override
		public String determineGeneratorName(GenerationType generationType, GeneratorNameDeterminationContext context) {
			switch ( generationType ) {
				case IDENTITY: {
					return "identity";
				}
				case SEQUENCE: {
					return org.hibernate.id.enhanced.SequenceStyleGenerator.class.getName();
				}
				case TABLE: {
					return org.hibernate.id.enhanced.TableGenerator.class.getName();
				}
				default: {
					// AUTO
					final Class javaType = context.getIdType();
					if ( UUID.class.isAssignableFrom( javaType ) ) {
						return UUIDGenerator.class.getName();
					}
					else {
						return org.hibernate.id.enhanced.SequenceStyleGenerator.class.getName();
					}
				}
			}
		}
