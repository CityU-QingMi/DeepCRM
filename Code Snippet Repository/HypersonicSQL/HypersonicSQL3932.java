        public int compare(Object o1, Object o2) {

            if (o1 == o2) {
                return 0;
            }

            if (o1 == null) {
                if (o2 == null) {
                    return 0;
                }

                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            return ((Comparable) o1).compareTo(o2);
        }
