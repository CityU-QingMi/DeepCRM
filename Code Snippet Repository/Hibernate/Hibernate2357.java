		@Override
		public final Boolean isUnsaved(Object version) throws MappingException {
			LOG.trace( "Version unsaved-value strategy NEGATIVE" );
			if ( version == null ) {
				return Boolean.TRUE;
			}
			if ( version instanceof Number ) {
				return ((Number) version).longValue() < 0L;
			}
			throw new MappingException( "unsaved-value NEGATIVE may only be used with short, int and long types" );
		}
