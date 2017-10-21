	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableB other = (TableB) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (tablea == null)
		{
			if (other.tablea != null)
				return false;
		}
		else if (!tablea.equals(other.tablea))
			return false;
		return true;
	}
