	protected void printFlashMap(FlashMap flashMap) throws Exception {
		if (ObjectUtils.isEmpty(flashMap)) {
			this.printer.printValue("Attributes", null);
		}
		else {
			for (String name : flashMap.keySet()) {
				this.printer.printValue("Attribute", name);
				this.printer.printValue("value", flashMap.get(name));
			}
		}
	}
