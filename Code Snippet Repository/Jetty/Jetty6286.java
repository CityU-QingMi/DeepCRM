        @Override
        public Calendar decode(String s) throws DecodeException
        {
            if (TZ == null)
                throw new DecodeException(s, ".init() not called");
            try
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                dateFormat.setTimeZone(TZ);
                Date time = dateFormat.parse(s);
                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TZ);
                cal.setTime(time);
                return cal;
            }
            catch (ParseException e)
            {
                throw new DecodeException(s, "Unable to decode Time", e);
            }
        }
