	public CustomerInventory addInventory(Item item, int quantity,
										  BigDecimal totalValue) {

		CustomerInventory inventory = new CustomerInventory(
				this, item,
				quantity, totalValue
		);
		getInventories().add( inventory );
		return inventory;
	}
