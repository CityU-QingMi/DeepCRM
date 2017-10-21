	public SpelNodeImpl(int pos, SpelNodeImpl... operands) {
		this.pos = pos;
		// pos combines start and end so can never be zero because tokens cannot be zero length
		Assert.isTrue(pos != 0, "Pos must not be 0");
		if (!ObjectUtils.isEmpty(operands)) {
			this.children = operands;
			for (SpelNodeImpl operand : operands) {
				Assert.notNull(operand, "Operand must not be null");
				operand.parent = this;
			}
		}
	}
