		public void processingFetch(Fetch fetch) {
			if ( ! hasSubselectFetch ) {
				if ( fetch.getFetchStrategy().getStyle() == FetchStyle.SUBSELECT
						&& fetch.getFetchStrategy().getTiming() != FetchTiming.IMMEDIATE ) {
					hasSubselectFetch = true;
				}
			}
			if ( isJoinFetchedBag( fetch ) ) {
				if ( joinedBagAttributeFetches == null ) {
					joinedBagAttributeFetches = new HashSet<>();
				}
				joinedBagAttributeFetches.add( (CollectionAttributeFetch) fetch );
			}
		}
