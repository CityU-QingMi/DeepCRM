        static Strategy getStrategy(final int tokenLen) {
            switch(tokenLen) {
            case 1:
                return ISO_8601_1_STRATEGY;
            case 2:
                return ISO_8601_2_STRATEGY;
            case 3:
                return ISO_8601_3_STRATEGY;
            default:
                throw new IllegalArgumentException("invalid number of X");
            }
        }
