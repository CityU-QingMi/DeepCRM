	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemPet)) {
			return false;
		}
		ItemPet otherPet = (ItemPet) other;
		return (this.name != null && this.name.equals(otherPet.getName()));
	}
