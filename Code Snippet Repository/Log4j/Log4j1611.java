    @Override
    public String toString() {
        switch (value) {
            case CONNECT_BYTE:
                return "Connect";
            case EHLO_BYTE:
                return "EHLO";
            case MAIL_BYTE:
                return "MAIL";
            case RCPT_BYTE:
                return "RCPT";
            case DATA_BYTE:
                return "DATA";
            case DATA_END_BYTE:
                return ".";
            case QUIT_BYTE:
                return "QUIT";
            case RSET_BYTE:
                return "RSET";
            case VRFY_BYTE:
                return "VRFY";
            case EXPN_BYTE:
                return "EXPN";
            case HELP_BYTE:
                return "HELP";
            case NOOP_BYTE:
                return "NOOP";
            case UNREC_BYTE:
                return "Unrecognized command / data";
            case BLANK_LINE_BYTE:
                return "Blank line";
            default:
                return "Unknown";
        }
    }
