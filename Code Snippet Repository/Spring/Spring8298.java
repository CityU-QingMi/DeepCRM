	private void applyDefaultResultActions(MvcResult mvcResult) throws Exception {

		for (ResultMatcher matcher : this.defaultResultMatchers) {
			matcher.match(mvcResult);
		}

		for (ResultHandler handler : this.defaultResultHandlers) {
			handler.handle(mvcResult);
		}
	}
