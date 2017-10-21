        @Override
        public long currentTimeMillis() {

            // improve granularity: also update time field every 1024 calls.
            // (the bit fiddling means we don't need to worry about overflows)
            if ((++count & UPDATE_THRESHOLD) == UPDATE_THRESHOLD) {
                millis = System.currentTimeMillis();
            }
            return millis;
        }
