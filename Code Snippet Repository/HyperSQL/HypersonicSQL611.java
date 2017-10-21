        public boolean next() {

            while (currentIndex >= 0) {
                currentRange = rangeIterators[currentIndex];

                if (currentRange.next()) {
                    if (currentIndex < rangeIterators.length - 1) {
                        currentIndex++;

                        continue;
                    }

                    return true;
                } else {
                    currentRange.reset();

                    currentIndex--;

                    continue;
                }
            }

            currentRange = null;

            for (int i = 0; i < rangeIterators.length; i++) {
                rangeIterators[i].reset();
            }

            return false;
        }
