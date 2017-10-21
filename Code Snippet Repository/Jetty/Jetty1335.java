    public static String getMessage(int code)
    {
        Code codeEnum = getCode(code);
        if (codeEnum != null)
        {
            return codeEnum.getMessage();
        }
        else
        {
            return Integer.toString(code);
        }
    }
