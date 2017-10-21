		@Override
		public ExceptionMapper getExceptionMapper() {
			if ( sessionOwner != null ) {
				return sessionOwner.getExceptionMapper();
			}
			else {
				return sessionOwnerBehavior == SessionOwnerBehavior.LEGACY_JPA
						? ExceptionMapperLegacyJpaImpl.INSTANCE
						: null;
			}
		}
