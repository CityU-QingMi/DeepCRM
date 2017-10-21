        public int compare(Node o1, Node o2)
        {
            if (o1.getName().equals(TOPNODE))
            {
                return -1;
            }

            if (o2.getName().equals(TOPNODE))
            {
                return 1;
            }

            CollationKey key1 = toKey(o1);
            CollationKey key2 = toKey(o2);
            return key1.compareTo(key2);
        }
