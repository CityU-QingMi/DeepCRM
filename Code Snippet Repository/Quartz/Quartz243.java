    @Override
    public Object clone()  {
        try {
            BaseCalendar clone = (BaseCalendar) super.clone();
            if (getBaseCalendar() != null) {
                clone.baseCalendar = (Calendar) getBaseCalendar().clone();
            }
            if(getTimeZone() != null)
                clone.timeZone = (TimeZone) getTimeZone().clone();
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new IncompatibleClassChangeError("Not Cloneable.");
        }
    }
