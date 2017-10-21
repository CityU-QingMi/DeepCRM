    @Override
    public String toString() {
        switch (value) {
            case CONNECT_BYTE:
                return "CONNECT";
            case GREET_BYTE:
                return "GREET";
            case MAIL_BYTE:
                return "MAIL";
            case RCPT_BYTE:
                return "RCPT";
            case DATA_HEADER_BYTE:
                return "DATA_HDR";
            case DATA_BODY_BYTE:
                return "DATA_BODY";
            case QUIT_BYTE:
                return "QUIT";
            default:
                return "Unknown";
        }
    }
