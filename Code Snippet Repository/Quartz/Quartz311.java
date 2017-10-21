    protected void storeCalendar(Connection conn, 
            String calName, Calendar calendar, boolean replaceExisting, boolean updateTriggers)
        throws JobPersistenceException {
        try {
            boolean existingCal = calendarExists(conn, calName);
            if (existingCal && !replaceExisting) { 
                throw new ObjectAlreadyExistsException(
                    "Calendar with name '" + calName + "' already exists."); 
            }

            if (existingCal) {
                if (getDelegate().updateCalendar(conn, calName, calendar) < 1) { 
                    throw new JobPersistenceException(
                        "Couldn't store calendar.  Update failed."); 
                }
                
                if(updateTriggers) {
                    List<OperableTrigger> trigs = getDelegate().selectTriggersForCalendar(conn, calName);
                    
                    for(OperableTrigger trigger: trigs) {
                        trigger.updateWithNewCalendar(calendar, getMisfireThreshold());
                        storeTrigger(conn, trigger, null, true, STATE_WAITING, false, false);
                    }
                }
            } else {
                if (getDelegate().insertCalendar(conn, calName, calendar) < 1) { 
                    throw new JobPersistenceException(
                        "Couldn't store calendar.  Insert failed."); 
                }
            }

            if (!isClustered) {
                calendarCache.put(calName, calendar); // lazy-cache
            }

        } catch (IOException e) {
            throw new JobPersistenceException(
                    "Couldn't store calendar because the BLOB couldn't be serialized: "
                            + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new JobPersistenceException("Couldn't store calendar: "
                    + e.getMessage(), e);
        }catch (SQLException e) {
            throw new JobPersistenceException("Couldn't store calendar: "
                    + e.getMessage(), e);
        }
    }
