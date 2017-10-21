    private synchronized String formatMessage(LogRecord record)
    {
        String msg = getMessage(record);

        try
        {
            Object params[] = record.getParameters();
            if ((params == null) || (params.length == 0))
            {
                return msg;
            }

            if (Pattern.compile("\\{\\d+\\}").matcher(msg).find())
            {
                return MessageFormat.format(msg,params);
            }

            return msg;
        }
        catch (Exception ex)
        {
            return msg;
        }
    }
