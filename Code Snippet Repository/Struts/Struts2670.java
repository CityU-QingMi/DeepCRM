    private int jjMoveStringLiteralDfa5_1(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_1(3, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_1(4, active0);
            return 5;
        }
        switch (curChar) {
            case 110:
                return jjMoveStringLiteralDfa6_1(active0, 0x100000000000L);
            default:
                break;
        }
        return jjStartNfa_1(4, active0);
    }
