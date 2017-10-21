        @Override
        public int estimateLength() {
            int max = 0;
            for (int i=mValues.length; --i >= 0; ) {
                final int len = mValues[i].length();
                if (len > max) {
                    max = len;
                }
            }
            return max;
        }
