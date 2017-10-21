    public String toStateString()
    {
        switch(_state.get().getType())
        {
            case WRITING:
                return "W";
            case PENDING:
                return "P";
            case COMPLETING:
                return "C";
            case IDLE:
                return "-";
            case FAILED:
                return "F";
            default:
                return "?";
        }
    }
