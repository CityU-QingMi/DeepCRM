        public void release() {

            if (it != null) {
                it.release();
            }

            for (int i = 0; i < rangeIterators.length; i++) {
                rangeIterators[i].reset();
            }
        }
