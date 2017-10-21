    private boolean setBuf(Token newBuffer) {
        if (buffer != null && buffer.equals(newBuffer)) return false;
        switch (newBuffer.type) {
            case Token.SQL_TYPE:
            case Token.PL_TYPE:
            case Token.SPECIAL_TYPE:
                break;
            default:
                assert false:
                    "Internal assertion failed.  "
                    + "Attempted to add command type "
                    + newBuffer.getTypeString() + " to buffer";
        }
        buffer = new Token(newBuffer.type, newBuffer.val, newBuffer.line);
        // System.err.println("Buffer is now (" + buffer + ')');
        return true;
    }
