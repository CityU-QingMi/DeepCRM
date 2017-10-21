    public Timestamp getPubTime(Locale locale, TimeZone timezone) {
        
        Timestamp pubtime = null;
        
        if(!StringUtils.isEmpty(getDateString())) {
            try {
                log.debug("pubtime vals are "+getDateString()+", "+getHours()+", "+getMinutes()+", "+getSeconds());

                // first convert the specified date string into an actual Date obj
                // TODO: at some point this date conversion should be locale sensitive,
                // however at this point our calendar widget does not take into account
                // locales and only operates in the standard English US locale.

                // Don't require user add preceding '0' of month and day.
                DateFormat df = new SimpleDateFormat("M/d/yy");
                df.setTimeZone(timezone);
                Date newDate = df.parse(getDateString());

                log.debug("dateString yields date - "+newDate);

                // Now handle the time from the hour, minute and second combos
                Calendar cal = Calendar.getInstance(timezone,locale);
                cal.setTime(newDate);
                cal.set(Calendar.HOUR_OF_DAY, getHours());
                cal.set(Calendar.MINUTE, getMinutes());
                cal.set(Calendar.SECOND, getSeconds());
                pubtime = new Timestamp(cal.getTimeInMillis());

                log.debug("pubtime is "+pubtime);
            } catch(Exception e) {
                log.error("Error calculating pubtime", e);
            }
        }
        
        return pubtime;
    }
