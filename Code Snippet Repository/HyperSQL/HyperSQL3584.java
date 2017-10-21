        public boolean next() {

            if (isOnRightOuterRows) {
                if (it == emptyIterator) {
                    return false;
                }

                return findNextRight();
            } else {
                return super.next();
            }
        }
