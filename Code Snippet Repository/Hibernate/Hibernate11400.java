		public void removeInvalidator(Object owner, Object key, long now, boolean doPFER) {
			if (invalidators == null) {
				if (singleInvalidator != null && singleInvalidator.owner.equals(owner)) {
					pferValueIfNeeded(owner, key, singleInvalidator.valueForPFER, doPFER);
					singleInvalidator = null;
				}
			}
			else {
				Invalidator invalidator = invalidators.remove(owner);
				if (invalidator != null) {
					pferValueIfNeeded(owner, key, invalidator.valueForPFER, doPFER);
				}
			}
			lastInvalidationEnd = Math.max(lastInvalidationEnd, now);
		}
