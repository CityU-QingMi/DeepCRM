		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("FixedBackOff{");
			sb.append("interval=").append(FixedBackOff.this.interval);
			String attemptValue = (FixedBackOff.this.maxAttempts == Long.MAX_VALUE ?
					"unlimited" : String.valueOf(FixedBackOff.this.maxAttempts));
			sb.append(", currentAttempts=").append(this.currentAttempts);
			sb.append(", maxAttempts=").append(attemptValue);
			sb.append('}');
			return sb.toString();
		}
