		@SuppressWarnings("")
		public TreatedMapAttributeJoin(MapAttributeJoin<O, K, ? super T> original, Class<T> treatAsType) {
			super(
					original.criteriaBuilder(),
					treatAsType,
					original.getPathSource(),
					(MapAttribute<? super O, K, T>) original.getAttribute(),
					original.getJoinType()
			);
			this.original = original;
			this.treatAsType = treatAsType;
		}
