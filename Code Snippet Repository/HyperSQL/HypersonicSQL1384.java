        public IndexRowIterator(Session session, PersistentStore store,
                                IndexAVL index, NodeAVL node,
                                int distinctCount, boolean single,
                                boolean reversed) {

            this.session       = session;
            this.store         = store;
            this.index         = index;
            this.distinctCount = distinctCount;
            this.single        = single;
            this.reversed      = reversed;

            if (index == null) {
                return;
            }

            nextnode = node;
        }
