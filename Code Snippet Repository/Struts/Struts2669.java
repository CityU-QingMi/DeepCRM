    private int jjMoveStringLiteralDfa4_1(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_1(2, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_1(3, active0);
            return 4;
        }
        switch (curChar) {
            case 97:
                return jjMoveStringLiteralDfa5_1(active0, 0x100000000000L);
            case 101:
                if ((active0 & 0x8000L) != 0L)
                    return jjStartNfaWithStates_1(4, 15, 36);
                break;
            case 121:
                if ((active0 & 0x80000000000L) != 0L)
                    return jjStartNfaWithStates_1(4, 43, 36);
                break;
            default:
                break;
        }
        return jjStartNfa_1(3, active0);
    }
