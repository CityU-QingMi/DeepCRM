		public FieldAttributeAccess(Field field) {
			this.name = field.getName();
			try {
				field.setAccessible( true );
			}
			catch (Exception e) {
				this.field = null;
				return;
			}
			this.field = field;
		}
