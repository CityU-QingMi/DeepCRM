		public Map<String, SimpSession> findSessions(String userName) {
			Map<String, SimpSession> map = new HashMap<>(1);
			SimpUser user = localRegistry.getUser(userName);
			if (user != null) {
				for (SimpSession session : user.getSessions()) {
					map.put(session.getId(), session);
				}
			}
			for (UserRegistrySnapshot registry : remoteRegistries.values()) {
				TransferSimpUser transferUser = registry.getUserMap().get(userName);
				if (transferUser != null) {
					transferUser.addSessions(map);
				}
			}
			return map;
		}
