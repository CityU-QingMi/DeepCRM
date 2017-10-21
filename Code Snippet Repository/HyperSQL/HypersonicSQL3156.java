        public boolean matches(byte[] candidate) {

            if (value.length != candidate.length) {
                return false;
            }

            return !BitMap.hasAnyBitSet(BitMap.xor(value,
                                                   BitMap.and(candidate,
                                                       mask)));
        }
