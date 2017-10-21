		@SuppressWarnings("")
		public Builder(MappingDefaults mappingDefaults) {
			//
			// todo : may need to add back a concept of DEFAULT fetch style / timing.
			// 		one option I like is adding a fetchTiming / fetchStyle and
			//		effectiveFetchTiming / effectiveFetchStyle.  The reasoning here
			//		is for Loaders and LoadPLan building

			fetchTiming = FetchTiming.DELAYED;
			fetchStyle = FetchStyle.SELECT;
		}
