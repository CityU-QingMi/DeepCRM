    private int jjMoveStringLiteralDfa3_1(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_1(1, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_1(2, active0);
            return 3;
        }
        switch (curChar) {
            case 101:
                if ((active0 & 0x4000L) != 0L)
                    return jjStartNfaWithStates_1(3, 14, 36);
                break;
            case 108:
                if ((active0 & 0x10000L) != 0L)
                    return jjStartNfaWithStates_1(3, 16, 36);
                break;
            case 115:
                return jjMoveStringLiteralDfa4_1(active0, 0x8000L);
            case 116:
                return jjMoveStringLiteralDfa4_1(active0, 0x180000000000L);
            default:
                break;
        }
        return jjStartNfa_1(2, active0);
    }
