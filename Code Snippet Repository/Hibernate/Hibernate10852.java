	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((component1 == null) ? 0 : component1.hashCode());
		result = prime * result
				+ ((component2 == null) ? 0 : component2.hashCode());
		result = prime * result
				+ ((component3 == null) ? 0 : component3.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
