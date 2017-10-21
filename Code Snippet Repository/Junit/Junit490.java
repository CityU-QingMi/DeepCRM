		@Test
		void additionNamespacePartMakesADifferenc() {

			Namespace ns1 = Namespace.create("part1", "part2");
			Namespace ns2 = Namespace.create("part1");
			Namespace ns3 = Namespace.create("part1", "part2");

			Object value2 = createObject("value2");

			parentStore.put(ns1, key, value);
			parentStore.put(ns2, key, value2);

			assertEquals(value, store.get(ns1, key));
			assertEquals(value, store.get(ns3, key));
			assertEquals(value2, store.get(ns2, key));
		}
