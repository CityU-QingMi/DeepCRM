	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof BasketItemsPK))
			return false;
		BasketItemsPK basketitemspk = (BasketItemsPK)aObj;
		if (getShoppingBaskets() == null && basketitemspk.getShoppingBaskets() != null)
			return false;
		if (!getShoppingBaskets().equals(basketitemspk.getShoppingBaskets()))
			return false;
		if ((getCost() != null && !getCost().equals(basketitemspk.getCost())) || (getCost() == null && basketitemspk.getCost() != null))
			return false;
		return true;
	}
