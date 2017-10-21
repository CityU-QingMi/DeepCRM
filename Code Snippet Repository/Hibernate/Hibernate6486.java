		@Override
		public String toString() {
			String result = getClass().getSimpleName() + " ";
			if ( id != null ) {
				result += "id: " + id;
			}
			result += ", version: " + version;
			if ( type != null && !type.trim().isEmpty() ) {
				result += ", type: " + type;
			}
			return result;
		}
