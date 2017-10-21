		@SuppressWarnings("")
		public TreatedSingularAttributePath(SingularAttributePath<? super T> original, Class<T> treatAsType) {
			super(
					original.criteriaBuilder(),
					treatAsType,
					original.getPathSource(),
					(SingularAttribute<?, T>) original.getAttribute()
			);
			this.original = original;
			this.treatAsType = treatAsType;
		}
