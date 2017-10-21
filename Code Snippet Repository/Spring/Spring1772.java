		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof MockFilter)) return false;

			final MockFilter mockFilter = (MockFilter) o;

			if (!age.equals(mockFilter.age)) return false;
			if (!extendedInfo.equals(mockFilter.extendedInfo)) return false;
			if (!name.equals(mockFilter.name)) return false;

			return true;
		}
