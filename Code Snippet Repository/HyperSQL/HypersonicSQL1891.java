        public void remove() {

            if (removed) {
                throw new NoSuchElementException("Iterator");
            }

            removed = true;

            if (counter != 0) {
                BaseList.this.remove(counter - 1);

                counter--;    // above can throw, so decrement if successful

                return;
            }

            throw new NoSuchElementException();
        }
