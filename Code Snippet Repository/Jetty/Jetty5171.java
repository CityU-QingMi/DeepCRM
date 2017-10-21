    protected void format(StringBuilder buffer, Throwable thrown, String indent)
    {
        if (thrown == null)
        {
            buffer.append("null");
        }
        else
        {
            buffer.append(EOL).append(indent);
            format(buffer,thrown.toString());
            StackTraceElement[] elements = thrown.getStackTrace();
            for (int i = 0; elements != null && i < elements.length; i++)
            {
                buffer.append(EOL).append(indent).append("\tat ");
                format(buffer,elements[i].toString());
            }

            for (Throwable suppressed:thrown.getSuppressed())
            {
                buffer.append(EOL).append(indent).append("Suppressed: ");
                format(buffer,suppressed,"\t|"+indent);
            }
            
            Throwable cause = thrown.getCause();
            if (cause != null && cause != thrown)
            {
                buffer.append(EOL).append(indent).append("Caused by: ");
                format(buffer,cause,indent);
            }
        }
    }
