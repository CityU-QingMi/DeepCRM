    public Iterator list() {
        boolean workedAtAll = false;

        Set<Object> settingList = new HashSet<>();
        UnsupportedOperationException e = null;

        for (Settings delegate : delegates) {
            try {
                Iterator list = delegate.list();

                while (list.hasNext()) {
                    settingList.add(list.next());
                }

                workedAtAll = true;
            } catch (UnsupportedOperationException ex) {
                e = ex;
                // Try next delegate
            }
        }

        if (!workedAtAll) {
            throw (e == null) ? new UnsupportedOperationException() : e;
        } else {
            return settingList.iterator();
        }
    }
