    private void format(StringBuilder buffer, String level, String msg, Throwable thrown)
    {
        format(buffer,level,msg);
        if (isHideStacks())
        {
            format(buffer,": "+String.valueOf(thrown));
        }
        else
        {
            format(buffer,thrown);
        }
    }
