    public boolean isAnyTextCacheModified() {

        Iterator it = textCacheList.values().iterator();

        while (it.hasNext()) {
            if (((TextCache) it.next()).isModified()) {
                return true;
            }
        }

        return false;
    }
