		public String toString() {
			if (lock.tryLock()) {
				try {
					StringBuilder sb = new StringBuilder();
					sb.append("{ PendingPuts=");
					if (singlePendingPut == null) {
						if (fullMap == null) {
							sb.append("[]");
						}
						else {
							sb.append(fullMap.values());
						}
					}
					else {
						sb.append('[').append(singlePendingPut).append(']');
					}
					sb.append(", Invalidators=");
					if (singleInvalidator == null) {
						if (invalidators == null) {
							sb.append("[]");
						}
						else {
							sb.append(invalidators.values());
						}
					}
					else {
						sb.append('[').append(singleInvalidator).append(']');
					}
					sb.append(", LastInvalidationEnd=");
					if (lastInvalidationEnd == Long.MIN_VALUE) {
						sb.append("<none>");
					}
					else {
						sb.append(lastInvalidationEnd);
					}
					return sb.append(", Removed=").append(removed).append("}").toString();
				}
				finally {
					lock.unlock();
				}
			}
			else {
				return "PendingPutMap: <locked>";
			}
		}
