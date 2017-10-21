        public boolean isBoxedPrimitive(final StringBuilder text) {
            final StringBuilder[] array = ringBuffer.get();
            if (array == null) {
                return false;
            }
            for (int i = 0; i < array.length; i++) {
                if (text == array[i]) {
                    return true;
                }
            }
            return false;
        }
