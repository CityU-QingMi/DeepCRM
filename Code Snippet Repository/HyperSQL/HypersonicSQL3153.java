        public AclEntry(byte[] value, int bitBlockSize,
                        boolean allow) throws AclFormatException {

            byte[] allOn = null;

            switch (value.length) {

                case 4 :
                    allOn = ALL_SET_4BYTES;
                    break;

                case 16 :
                    allOn = ALL_SET_16BYTES;
                    break;

                default :
                    throw new IllegalArgumentException(
                        "Only 4 and 16 bytes supported, not " + value.length);
            }

            if (bitBlockSize > value.length * 8) {
                throw new IllegalArgumentException(
                    "Specified " + bitBlockSize
                    + " significant bits, but value only has "
                    + (value.length * 8) + " bits");
            }

            this.bitBlockSize = bitBlockSize;
            this.value        = value;
            mask = BitMap.leftShift(allOn, value.length * 8 - bitBlockSize);

            if (mask.length != value.length) {
                throw new RuntimeException(
                    "Basic program assertion failed.  "
                    + "Generated mask length " + mask.length
                    + " (bytes) does not match given value length "
                    + value.length + " (bytes).");
            }

            this.allow = allow;

            validateMask();
        }
