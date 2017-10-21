	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfId other = (ConfId) obj;
		if (confKey == null) {
			if (other.confKey != null)
				return false;
		} else if (!confKey.equals(other.confKey))
			return false;
		else if (confValue == null) {
			if (other.confValue != null)
				return false;
		} else if (!confValue.equals(other.confValue))
			return false;
		return true;
	}
