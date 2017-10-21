	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((refEntities1 == null) ? 0 : refEntities1.hashCode());
		result = prime * result
				+ ((refEntities2 == null) ? 0 : refEntities2.hashCode());
		return result;
	}
