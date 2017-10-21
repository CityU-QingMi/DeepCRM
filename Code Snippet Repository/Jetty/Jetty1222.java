    public static boolean isSupportedProtocol(String protocol)
    {
        switch(protocol)
        {
            case "h2":
            case "h2-17":
            case "h2-16":
            case "h2-15":
            case "h2-14":
            case "h2c":
            case "h2c-17":
            case "h2c-16":
            case "h2c-15":
            case "h2c-14":
                return true;
            default:
                return false;
        }
    }
