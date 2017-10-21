		public static DescriptorComparison checkNumericCompatibility(
				@Nullable String leftDeclaredDescriptor, @Nullable String rightDeclaredDescriptor,
				@Nullable String leftActualDescriptor, @Nullable String rightActualDescriptor) {

			String ld = leftDeclaredDescriptor;
			String rd = rightDeclaredDescriptor;

			boolean leftNumeric = CodeFlow.isPrimitiveOrUnboxableSupportedNumberOrBoolean(ld);
			boolean rightNumeric = CodeFlow.isPrimitiveOrUnboxableSupportedNumberOrBoolean(rd);
			
			// If the declared descriptors aren't providing the information, try the actual descriptors
			if (!leftNumeric && !ObjectUtils.nullSafeEquals(ld, leftActualDescriptor)) {
				ld = leftActualDescriptor;
				leftNumeric = CodeFlow.isPrimitiveOrUnboxableSupportedNumberOrBoolean(ld);
			}
			if (!rightNumeric && !ObjectUtils.nullSafeEquals(rd, rightActualDescriptor)) {
				rd = rightActualDescriptor;
				rightNumeric = CodeFlow.isPrimitiveOrUnboxableSupportedNumberOrBoolean(rd);
			}
			
			if (leftNumeric && rightNumeric) {
				if (CodeFlow.areBoxingCompatible(ld, rd)) {
					return new DescriptorComparison(true, true, CodeFlow.toPrimitiveTargetDesc(ld));
				}
				else {
					return DescriptorComparison.INCOMPATIBLE_NUMBERS;
				}
			}
			else {
				return DescriptorComparison.NOT_NUMBERS;
			}		
		}
