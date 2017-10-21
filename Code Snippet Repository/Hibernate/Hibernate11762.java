		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			else if (obj instanceof Item) {
				return itemId == ((Item) obj).itemId;
			}
			else {
				return false;
			}
		}
