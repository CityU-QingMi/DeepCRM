	private void computeExitTypeDescriptor() {
		if (this.exitTypeDescriptor == null && this.children[1].exitTypeDescriptor != null &&
				this.children[2].exitTypeDescriptor != null) {
			String leftDescriptor = this.children[1].exitTypeDescriptor;
			String rightDescriptor = this.children[2].exitTypeDescriptor;
			if (ObjectUtils.nullSafeEquals(leftDescriptor, rightDescriptor)) {
				this.exitTypeDescriptor = leftDescriptor;
			}
			else {
				// Use the easiest to compute common super type
				this.exitTypeDescriptor = "Ljava/lang/Object";
			}
		}
	}
