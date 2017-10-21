		@Override
		protected Comparator<String> getMappingComparator(final Message<?> message) {
			return new Comparator<String>() {
				@Override
				public int compare(String info1, String info2) {
					DestinationPatternsMessageCondition cond1 = new DestinationPatternsMessageCondition(info1);
					DestinationPatternsMessageCondition cond2 = new DestinationPatternsMessageCondition(info2);
					return cond1.compareTo(cond2, message);
				}
			};
		}
