	private void pushPathElement(PathElement newPathElement) {
		if (newPathElement instanceof CaptureTheRestPathElement) {
			// There must be a separator ahead of this thing
			// currentPE SHOULD be a SeparatorPathElement
			if (this.currentPE == null) {
				this.headPE = newPathElement;
				this.currentPE = newPathElement;
			}
			else if (this.currentPE instanceof SeparatorPathElement) {
				PathElement peBeforeSeparator = this.currentPE.prev;
				if (peBeforeSeparator == null) {
					// /{*foobar} is at the start
					this.headPE = newPathElement;
					newPathElement.prev = null;
				}
				else {
					peBeforeSeparator.next = newPathElement;
					newPathElement.prev = peBeforeSeparator;
				}
				this.currentPE = newPathElement;
			}
			else {
				throw new IllegalStateException("Expected SeparatorPathElement but was " + this.currentPE);
			}
		}
		else {
			if (this.headPE == null) {
				this.headPE = newPathElement;
				this.currentPE = newPathElement;
			}
			else if (this.currentPE != null) {
				this.currentPE.next = newPathElement;
				newPathElement.prev = this.currentPE;
				this.currentPE = newPathElement;
			}
		}

		resetPathElementState();
	}
