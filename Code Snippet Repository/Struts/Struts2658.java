    private int jjMoveStringLiteralDfa8_1(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_1(6, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_1(7, active0);
            return 8;
        }
        switch (curChar) {
            case 111:
                return jjMoveStringLiteralDfa9_1(active0, 0x100000000000L);
            default:
                break;
        }
        return jjStartNfa_1(7, active0);
    }
