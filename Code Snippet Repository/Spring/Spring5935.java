		public boolean hasAnyRole(String... roles) {
			if (roles == null) return true;
			String[] myRoles = getRoles();
			for (int i = 0; i < myRoles.length; i++) {
				for (int j = 0; j < roles.length; j++) {
					if (myRoles[i].equals(roles[j])) return true;
				}
			}
			return false;
		}
