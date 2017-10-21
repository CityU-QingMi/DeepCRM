		public boolean matches(String publicId, String systemId) {
			if ( publicId != null ) {
				if ( publicId.startsWith( identifierBase ) ) {
					return true;
				}
			}

			if ( systemId != null ) {
				if ( systemId.startsWith( identifierBase ) ) {
					return true;
				}
			}

			return false;
		}
