    private void notifyRemove(String username)
    {
        if (_listeners != null)
        {
            for (Iterator<UserListener> i = _listeners.iterator(); i.hasNext();)
            {
                i.next().remove(username);
            }
        }
    }
