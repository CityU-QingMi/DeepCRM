    public void addLeftColumnsForAllAny(RangeVariable range,
                                        OrderedIntHashSet set) {

        if (nodes.length == 0) {
            return;
        }

        for (int j = 0; j < nodes[LEFT].nodes.length; j++) {
            int index = nodes[LEFT].nodes[j].getColumnIndex();

            if (index < 0
                    || nodes[LEFT].nodes[j].getRangeVariable() != range) {
                set.clear();

                return;
            }

            set.add(index);
        }
    }
