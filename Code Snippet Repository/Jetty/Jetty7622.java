    private Map<String, Member> getRoom(String path)
    {
        Map<String, Member> room = _rooms.get(path);
        if (room == null)
        {
            LOG.debug("room: {} doesn't exist. Creating new room.", path);
            room = new HashMap<>();
            _rooms.put(path, room);
        }
        return room;
    }
