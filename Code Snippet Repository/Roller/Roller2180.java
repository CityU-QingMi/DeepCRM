    public List<UserWrapper> getItems() {
        
        if (users == null) {
            // calculate offset
            int offset = getPage() * length;
            
            List<UserWrapper> results = new ArrayList<UserWrapper>();
            try {
                Weblogger roller = WebloggerFactory.getWeblogger();
                UserManager umgr = roller.getUserManager();
                List<User> rawUsers;
                if (letter == null) {
                    rawUsers = umgr.getUsers(Boolean.TRUE, null, null, offset, length + 1);
                } else {
                    rawUsers = umgr.getUsersByLetter(letter.charAt(0), offset, length + 1);
                }
                
                // wrap the results
                int count = 0;
                for (User user : rawUsers) {
                    if (count++ < length) {
                        results.add(UserWrapper.wrap(user));
                    } else {
                        more = true;
                    }
                }
                
            } catch (Exception e) {
                log.error("ERROR: fetching user list", e);
            }
            
            users = results;
        }
        
        return users;
    }
