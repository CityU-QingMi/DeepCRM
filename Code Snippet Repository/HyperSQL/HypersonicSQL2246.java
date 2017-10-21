        public static int indexOf(byte[] ba, byte val, int from, int to) {

            for (int i = from; i < to; i++) {
                if (ba[i] == val) {
                    return i - from;
                }
            }

            return -1;
        }
