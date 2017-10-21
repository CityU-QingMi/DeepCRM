	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof ShoppingBasketsPK))
			return false;
		ShoppingBasketsPK shoppingbasketspk = (ShoppingBasketsPK)aObj;
		if (getOwner() == null && shoppingbasketspk.getOwner() != null)
			return false;
		if (!getOwner().equals(shoppingbasketspk.getOwner()))
			return false;
		if (getBasketDatetime() != shoppingbasketspk.getBasketDatetime())
			return false;
		return true;
	}
