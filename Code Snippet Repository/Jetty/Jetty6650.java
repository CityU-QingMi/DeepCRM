    private boolean parseHeader(String line) throws ParseException
    {
        switch (state)
        {
            case STATUS_LINE:
            {
                Matcher mat = PAT_STATUS_LINE.matcher(line);
                if (!mat.matches())
                {
                    throw new ParseException("Unexpected HTTP response status line [" + line + "]");
                }

                try
                {
                    listener.setStatusCode(Integer.parseInt(mat.group(1)));
                }
                catch (NumberFormatException e)
                {
                    throw new ParseException("Unexpected HTTP response status code",e);
                }
                listener.setStatusReason(mat.group(2));
                state = State.HEADER;
                break;
            }
            case HEADER:
            {
                if (StringUtil.isBlank(line))
                {
                    state = State.END;
                    return parseHeader(line);
                }

                Matcher header = PAT_HEADER.matcher(line);
                if (header.matches())
                {
                    String headerName = header.group(1);
                    String headerValue = header.group(2);
                    // do need to split header/value if comma delimited?
                    listener.addHeader(headerName,headerValue);
                }
                break;
            }
            case END:
                state = State.STATUS_LINE;
                return true;
        }
        return false;
    }
