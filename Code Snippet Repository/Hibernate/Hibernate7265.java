		public boolean equals(Object other) {
			if (other instanceof Id) {
				Id that = (Id) other;
				return that.customerId.equals(this.customerId) &&
					that.orderNumber == this.orderNumber;
			}
			else {
				return false;
			}
		}
