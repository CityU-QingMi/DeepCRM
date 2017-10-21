		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof SimpSubscription)) {
				return false;
			}
			SimpSubscription otherSubscription = (SimpSubscription) other;
			return (getId().equals(otherSubscription.getId()) &&
					getSession().getId().equals(otherSubscription.getSession().getId()));
		}
