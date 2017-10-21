		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TableBId other = (TableBId) obj;
			if (firstId == null)
			{
				if (other.firstId != null)
					return false;
			}
			else if (!firstId.equals(other.firstId))
				return false;
			if (secondId == null)
			{
				if (other.secondId != null)
					return false;
			}
			else if (!secondId.equals(other.secondId))
				return false;
			if (thirdId == null)
			{
				if (other.thirdId != null)
					return false;
			}
			else if (!thirdId.equals(other.thirdId))
				return false;
			return true;
		}
