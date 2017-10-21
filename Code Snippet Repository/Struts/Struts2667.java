    private int jjMoveStringLiteralDfa2_1(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_1(0, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_1(1, active0);
            return 2;
        }
        switch (curChar) {
            case 100:
                if ((active0 & 0x10000000000L) != 0L)
                    return jjStartNfaWithStates_1(2, 40, 36);
                else if ((active0 & 0x10000000000000L) != 0L)
                    return jjStartNfaWithStates_1(2, 52, 36);
                break;
            case 108:
                return jjMoveStringLiteralDfa3_1(active0, 0x18000L);
            case 112:
                return jjMoveStringLiteralDfa3_1(active0, 0x80000000000L);
            case 115:
                return jjMoveStringLiteralDfa3_1(active0, 0x100000000000L);
            case 116:
                if ((active0 & 0x4000000000L) != 0L)
                    return jjStartNfaWithStates_1(2, 38, 36);
                break;
            case 117:
                return jjMoveStringLiteralDfa3_1(active0, 0x4000L);
            case 118:
                if ((active0 & 0x4000000000000L) != 0L)
                    return jjStartNfaWithStates_1(2, 50, 36);
                break;
            default:
                break;
        }
        return jjStartNfa_1(1, active0);
    }
