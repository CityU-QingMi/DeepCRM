    private int jjMoveStringLiteralDfa6_1(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_1(4, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_1(5, active0);
            return 6;
        }
        switch (curChar) {
            case 99:
                return jjMoveStringLiteralDfa7_1(active0, 0x100000000000L);
            default:
                break;
        }
        return jjStartNfa_1(5, active0);
    }
