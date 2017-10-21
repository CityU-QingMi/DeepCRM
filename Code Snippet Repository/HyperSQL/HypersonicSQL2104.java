    public int addAll(LongDeque deque) {

        int count = 0;

        for (int i = 0; i < deque.size(); i++) {
            add(deque.get(i));

            count++;
        }

        return count;
    }
