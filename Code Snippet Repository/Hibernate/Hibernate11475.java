		@Override
		public Set toSet() {
			HashSet set = new HashSet();
			CloseableIterator it = iterator();
			try {
				while (it.hasNext()) {
					set.add(it.next());
				}
			}
			finally {
				it.close();
			}
			return set;
		}
