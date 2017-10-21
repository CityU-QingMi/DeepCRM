		public void addInvalidator(Object owner, Object valueForPFER, long now) {
			assert owner != null;
			if (invalidators == null) {
				if (singleInvalidator == null) {
					singleInvalidator = new Invalidator(owner, now, valueForPFER);
					put(new PendingPut(owner));
				}
				else {
					if (singleInvalidator.registeredTimestamp + expirationPeriod < now) {
						// override leaked invalidator
						singleInvalidator = new Invalidator(owner, now, valueForPFER);
						put(new PendingPut(owner));
					}
					invalidators = new HashMap<Object, Invalidator>();
					invalidators.put(singleInvalidator.owner, singleInvalidator);
					// with multiple invalidations the PFER must not be executed
					invalidators.put(owner, new Invalidator(owner, now, null));
					singleInvalidator = null;
				}
			}
			else {
				long allowedRegistration = now - expirationPeriod;
				// remove leaked invalidators
				for (Iterator<Invalidator> it = invalidators.values().iterator(); it.hasNext(); ) {
					if (it.next().registeredTimestamp < allowedRegistration) {
						it.remove();
					}
				}
				// With multiple invalidations in parallel we don't know the order in which
				// the writes were applied into DB and therefore we can't update the cache
				// with the most recent value.
				if (invalidators.isEmpty()) {
					put(new PendingPut(owner));
				}
				else {
					valueForPFER = null;
				}
				invalidators.put(owner, new Invalidator(owner, now, valueForPFER));
			}
		}
