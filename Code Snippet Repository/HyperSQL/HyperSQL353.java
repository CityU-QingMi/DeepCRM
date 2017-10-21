    public Token(int inType, String inVal, int inLine) {
        val = inVal; type = inType; line = inLine + 1;
        switch (inType) {
            case SPECIAL_TYPE:
            case EDIT_TYPE:
            case PL_TYPE:
            case MACRO_TYPE:
                // These types must be not null.  May be just whitespace.
                // Will be trimmed.
                if (val == null) throw new IllegalArgumentException(
                        "Null String value for scanner token");
                // Leading white space is always safe for us to trim from these
                // types of commands, but we must preserve trailing whitespace
                // for some commands.
                val = leadingWhitePattern.matcher(val).replaceFirst("");
                break;

            case SYNTAX_ERR_TYPE:
            case BUFFER_TYPE:
            case RAW_TYPE:
            case RAWEXEC_TYPE:
            case UNTERM_TYPE:
                // These types must be not null.  May be just whitespace.
                // Will NOT be trimmed.
                if (val == null) throw new IllegalArgumentException(
                        "Null String value for scanner token");
                break;

            case SQL_TYPE:
                // These types may be anything (null, whitespace, etc.).
                // Will NOT be trimmed
                break;

            default: throw new IllegalArgumentException(
                "Internal error.  Unexpected scanner token type: " + inType);
        }
    }
