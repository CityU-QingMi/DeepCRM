    private int jjMoveStringLiteralDfa9_1(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_1(7, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_1(8, active0);
            return 9;
        }
        switch (curChar) {
            case 102:
                if ((active0 & 0x100000000000L) != 0L)
                    return jjStartNfaWithStates_1(9, 44, 36);
                break;
            default:
                break;
        }
        return jjStartNfa_1(8, active0);
    }
