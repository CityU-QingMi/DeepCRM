	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableA other = (TableA) obj;
		if (id != other.id)
			return false;
		if (test == null)
		{
			if (other.test != null)
				return false;
		}
		else if (!test.equals(other.test))
			return false;
		if (test2 == null)
		{
			if (other.test2 != null)
				return false;
		}
		else if (!test2.equals(other.test2))
			return false;
		return true;
	}
