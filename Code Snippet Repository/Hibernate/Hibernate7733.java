	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultipleCollectionEntity other = (MultipleCollectionEntity) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (refEntities1 == null) {
			if (other.refEntities1 != null)
				return false;
		} else if (!refEntities1.equals(other.refEntities1))
			return false;
		if (refEntities2 == null) {
			if (other.refEntities2 != null)
				return false;
		} else if (!refEntities2.equals(other.refEntities2))
			return false;
		return true;
	}
