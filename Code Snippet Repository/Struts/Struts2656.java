    private final int jjStopStringLiteralDfa_0(int pos, long active0) {
        switch (pos) {
            case 0:
                if ((active0 & 0x10L) != 0L)
                    return 2;
                if ((active0 & 0x4L) != 0L) {
                    jjmatchedKind = 1;
                    return 4;
                }
                if ((active0 & 0x8L) != 0L) {
                    jjmatchedKind = 1;
                    return 6;
                }
                return -1;
            default:
                return -1;
        }
    }
