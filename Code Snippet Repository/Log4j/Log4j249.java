        public StringBuilder getStringBuilder() {
            StringBuilder[] array = ringBuffer.get();
            if (array == null) {
                array = new StringBuilder[RINGBUFFER_SIZE];
                for (int i = 0; i < array.length; i++) {
                    array[i] = new StringBuilder(21);
                }
                ringBuffer.set(array);
                current.set(new int[1]);
            }
            final int[] index = current.get();
            final StringBuilder result = array[MASK & index[0]++];
            result.setLength(0);
            return result;
        }
