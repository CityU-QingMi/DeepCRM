		@Override
		public E get(int index) {
			if (index >= list.size()) {
				for (int i = list.size(); i < index; i++) {
					list.add(null);
				}
				list.add(null);
				return null;
			}
			else {
				return list.get(index);
			}
		}
