	public Employee setSkills(Employee employee, List<String> skillNames) {
		if (employee != null && skillNames != null) {
			employee.setOtherSkills(new ArrayList());
			for (int i = 0, j = skillNames.size(); i < j; i++) {
				Skill skill = (Skill) skillDao.get(skillNames.get(i));
				employee.getOtherSkills().add(skill);
			}
		}
		return employee;
	}
