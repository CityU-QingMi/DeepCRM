    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("StdErrLog:");
        s.append(_name);
        s.append(":LEVEL=");
        switch (_level)
        {
            case LEVEL_ALL:
                s.append("ALL");
                break;
            case LEVEL_DEBUG:
                s.append("DEBUG");
                break;
            case LEVEL_INFO:
                s.append("INFO");
                break;
            case LEVEL_WARN:
                s.append("WARN");
                break;
            default:
                s.append("?");
                break;
        }
        return s.toString();
    }
