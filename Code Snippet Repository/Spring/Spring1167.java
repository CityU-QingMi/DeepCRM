		private void setNameFromPrincipal(Set<Principal> principals) {
			if (principals == null) {
				return;
			}
			for (Iterator<Principal> it = principals.iterator(); it.hasNext();) {
				Principal p = it.next();
				this.userName = p.getName();
				return;
			}
		}
