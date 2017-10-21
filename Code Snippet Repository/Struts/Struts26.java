	public void logout(String name) {
		assert (name != null);
		assert (name.trim().length() > 0);
		availableUsers.remove(name);
		for (Room room : availableRooms.values()) {
			if (room.hasMember(name)) {
				room.memberExit(name);
			}
		}
	}
