		static IntegerTestBean[] createArray() {
			IntegerTestBean[] array = new IntegerTestBean[3];
			for (int i = 0; i < 3; i++) {
				if (i == 1) {
					array[i] = new IntegerTestBean(5.9f);
				}
				else {
					array[i] = new IntegerTestBean(i + 5);
				}
			}
			return array;
		}
