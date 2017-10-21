	public String execute() throws Exception {
		if (getCurrentEmployee() != null && getCurrentEmployee().getOtherSkills() != null) {
			setSelectedSkills(new ArrayList<String>());
			Iterator it = getCurrentEmployee().getOtherSkills().iterator();
			while (it.hasNext()) {
				getSelectedSkills().add(((Skill) it.next()).getName());
			}
		}
		return super.execute();
	}
