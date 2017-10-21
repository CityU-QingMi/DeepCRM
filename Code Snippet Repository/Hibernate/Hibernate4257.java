		public ParameterMemento(
				int position,
				String name,
				ParameterMode mode,
				Class type,
				Type hibernateType,
				boolean passNulls) {
			this.position = position;
			this.name = name;
			this.mode = mode;
			this.type = type;
			this.hibernateType = hibernateType;
			this.passNulls = passNulls;
		}
