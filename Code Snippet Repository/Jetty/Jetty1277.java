    private long parse(final String dateVal)
    {
        for (int i = 0; i < _dateReceive.length; i++)
        {
            if (_dateReceive[i] == null)
            {
                _dateReceive[i] = new SimpleDateFormat(__dateReceiveFmt[i], Locale.US);
                _dateReceive[i].setTimeZone(__GMT);
            }

            try
            {
                Date date = (Date) _dateReceive[i].parseObject(dateVal);
                return date.getTime();
            }
            catch (java.lang.Exception e)
            {
                // LOG.ignore(e);
            }
        }

        if (dateVal.endsWith(" GMT"))
        {
            final String val = dateVal.substring(0, dateVal.length() - 4);

            for (SimpleDateFormat element : _dateReceive)
            {
                try
                {
                    Date date = (Date) element.parseObject(val);
                    return date.getTime();
                }
                catch (java.lang.Exception e)
                {
                    // LOG.ignore(e);
                }
            }
        }
        return -1;
    }
