		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((firstId == null) ? 0 : firstId.hashCode());
			result = prime * result + ((secondId == null) ? 0 : secondId.hashCode());
			result = prime * result + ((thirdId == null) ? 0 : thirdId.hashCode());
			return result;
		}
