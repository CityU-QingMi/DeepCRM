	protected boolean isCompilableOperatorUsingNumerics() {
		SpelNodeImpl left = getLeftOperand();
		SpelNodeImpl right = getRightOperand();
		if (!left.isCompilable() || !right.isCompilable()) {
			return false;
		}

		// Supported operand types for equals (at the moment)
		String leftDesc = left.exitTypeDescriptor;
		String rightDesc = right.exitTypeDescriptor;
		DescriptorComparison dc = DescriptorComparison.checkNumericCompatibility(
				leftDesc, rightDesc, this.leftActualDescriptor, this.rightActualDescriptor);
		return (dc.areNumbers && dc.areCompatible);
	}
