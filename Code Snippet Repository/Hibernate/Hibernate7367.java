	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ATable other = (ATable) obj;
		if (firstId == null)
		{
			if (other.firstId != null)
				return false;
		}
		else if (!firstId.equals(other.firstId))
			return false;
		if (tablebs == null)
		{
			if (other.tablebs != null)
				return false;
		}
		else if (!tablebs.equals(other.tablebs))
			return false;
		return true;
	}
