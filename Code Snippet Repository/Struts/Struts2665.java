    private int jjMoveStringLiteralDfa1_0(long active0) {
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
        switch (curChar) {
            case 123:
                if ((active0 & 0x4L) != 0L)
                    return jjStopAtPos(1, 2);
                else if ((active0 & 0x8L) != 0L)
                    return jjStopAtPos(1, 3);
                break;
            default:
                break;
        }
        return jjStartNfa_0(0, active0);
    }
