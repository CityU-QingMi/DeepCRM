    private Member getMember(String username, Map<String, Member> room)
    {
        Member member = room.get(username);
        if (member == null)
        {
            LOG.debug("user: {} in room: {} doesn't exist. Creating new user.", username, room);
            member = new Member(username);
            room.put(username, member);
        }
        return member;
    }
