	public CustomerInventoryTwo addInventory(Item item, int quantity,
										  BigDecimal totalValue) {

		CustomerInventoryTwo inventory = new CustomerInventoryTwo(
				this, item,
				quantity, totalValue
		);
		getInventories().add( inventory );
		return inventory;
	}
